package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.Signature
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Transaction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction._VersionedTransactionV1
import java.security.KeyPair
import java.time.Duration
import java.time.Instant

class TransactionBuilder(builder: TransactionBuilder.() -> Unit = {}) {

    var accountId: Id?
    val instructions: Lazy<ArrayList<Instruction>>
    var creationTimeMillis: ULong?
    var timeToLiveMillis: ULong?
    var metadata: Lazy<HashMap<String, Value>>

    init {
        accountId = null
        instructions = lazy { ArrayList() }
        creationTimeMillis = null
        timeToLiveMillis = null
        metadata = lazy { HashMap() }
        builder(this)
    }

    fun account(accountId: Id) = this.apply { this.accountId = accountId }

    fun account(accountName: String, domain: String) = this.account(Id(accountName, domain))

    fun instructions(vararg instructions: Instruction) = this.apply { this.instructions.value.addAll(instructions) }

    fun instructions(instructions: Iterable<Instruction>) = this.apply { this.instructions.value.addAll(instructions) }

    fun instruction(instruction: Instruction) = this.apply { this.instructions.value.add(instruction) }

    inline fun instruction(instruction: Instructions.() -> Instruction) =
        this.apply { this.instructions.value.add(instruction(Instructions)) }

    fun creationTime(creationTimeMillis: ULong) = this.apply { this.creationTimeMillis = creationTimeMillis }

    fun creationTime(creationTime: Instant) = this.apply { this.creationTime(creationTime.toEpochMilli()) }

    fun creationTime(creationTimeMillis: Long) = this.apply { this.creationTime(creationTimeMillis.toULong()) }

    fun timeToLive(ttlMillis: ULong) = this.apply { this.timeToLiveMillis = ttlMillis }

    fun timeToLive(ttl: Duration) = this.apply { this.timeToLive(ttl.toMillis()) }

    fun timeToLive(ttlMillis: Long) = this.apply { this.timeToLive(ttlMillis.toULong()) }

    fun buildSigned(vararg keyPairs: KeyPair): VersionedTransaction {
        val payload = Payload(
            checkNotNull(accountId) { "Account Id of the sender is mandatory" },
            instructions.value,
            creationTimeMillis ?: fallbackCreationTime(),
            timeToLiveMillis ?: DURATION_OF_24_HOURS_IN_MILLIS,
            metadata.value
        )
        val encodedPayload = payload.encode(Payload)

        val signatures = keyPairs.map {
            Signature(
                it.public.toIrohaPublicKey(),
                it.private.sign(encodedPayload)
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
