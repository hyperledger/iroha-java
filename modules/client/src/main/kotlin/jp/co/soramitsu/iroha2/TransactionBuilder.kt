package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.Signature
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Transaction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction._VersionedTransactionV1
import jp.co.soramitsu.iroha2.utils.encode
import jp.co.soramitsu.iroha2.utils.hash
import jp.co.soramitsu.iroha2.utils.sign
import jp.co.soramitsu.iroha2.utils.toIrohaPublicKey
import java.security.KeyPair
import java.time.Duration
import java.time.Instant

class TransactionBuilder private constructor() {

    var accountId: Id? = null
    val instructions = lazy { ArrayList<Instruction>() }
    var creationTimeMillis: ULong? = null
    var timeToLiveMillis: ULong? = null
    var metadata = lazy { HashMap<String, Value>() }

    fun account(accountId: Id) = this.apply { this.accountId = accountId }

    fun account(accountName: String, domain: String) = this.account(Id(accountName, domain))

    fun instructions(vararg instructions: Instruction) = this.apply { this.instructions.value.addAll(instructions) }

    fun instructions(instructions: Iterable<Instruction>) = this.apply { this.instructions.value.addAll(instructions) }

    fun instruction(instruction: Instruction) = this.apply { instructions.value.add(instruction) }

    inline fun instruction(instruction: Instructions.() -> Instruction) =
        this.apply { instructions.value.add(instruction(Instructions)) }

    fun creationTime(creationTimeMillis: ULong) = this.apply { this.creationTimeMillis = creationTimeMillis }

    fun creationTime(creationTime: Instant) = this.apply { this.creationTime(creationTime.toEpochMilli()) }

    fun creationTime(creationTimeMillis: Long) = this.apply { this.creationTime(creationTimeMillis.toULong()) }

    fun timeToLive(ttlMillis: ULong) = this.apply { this.timeToLiveMillis = ttlMillis }

    fun timeToLive(ttl: Duration) = this.apply { this.timeToLive(ttl.toMillis()) }

    fun timeToLive(ttlMillis: Long) = this.apply { this.timeToLive(ttlMillis.toULong()) }

    fun buildSigned(vararg keyPairs: KeyPair): VersionedTransaction {
        check(keyPairs.isNotEmpty()) {"At least one key par to sign must be specified"}

        val payload = Payload(
            checkNotNull(accountId) { "Account Id of the sender is mandatory" },
            instructions.value,
            creationTimeMillis ?: fallbackCreationTime(),
            timeToLiveMillis ?: DURATION_OF_24_HOURS_IN_MILLIS,
            metadata.value
        )
        val encodedPayload = encode(Payload, payload)

       val signatures = keyPairs.map {
           Signature(
               it.public.toIrohaPublicKey(),
               it.private.sign(encodedPayload.hash())
           )
       }.toMutableList()

        return VersionedTransaction.V1(
            _VersionedTransactionV1(
                Transaction(
                    payload,
                    signatures
                )
            )
        )
    }

    private fun fallbackCreationTime() = System.currentTimeMillis().toULong()

    private fun fallBackMetadata() = mutableMapOf<String, Value>()

    companion object {
        fun builder() = TransactionBuilder()

        val DURATION_OF_24_HOURS_IN_MILLIS = Duration.ofHours(24).toMillis().toULong()
    }
}
