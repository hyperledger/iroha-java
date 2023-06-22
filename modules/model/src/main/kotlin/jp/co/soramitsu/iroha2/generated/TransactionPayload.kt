//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.comparator
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigInteger
import kotlin.Long
import kotlin.collections.Map

/**
 * TransactionPayload
 *
 * Generated from 'TransactionPayload' regular structure
 */
public data class TransactionPayload(
    public val accountId: AccountId,
    public val instructions: Executable,
    public val creationTime: BigInteger,
    public val timeToLiveMs: BigInteger,
    public val nonce: Long? = null,
    public val metadata: Map<Name, Value>
) {
    public companion object : ScaleReader<TransactionPayload>, ScaleWriter<TransactionPayload> {
        public override fun read(reader: ScaleCodecReader): TransactionPayload = try {
            TransactionPayload(
                AccountId.read(reader),
                Executable.read(reader),
                reader.readUint64(),
                reader.readUint64(),
                reader.readNullable(),
                reader.readMap(reader.readCompactInt(), { Name.read(reader) }, { Value.read(reader) }),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: TransactionPayload) = try {
            AccountId.write(writer, instance.accountId)
            Executable.write(writer, instance.instructions)
            writer.writeUint64(instance.creationTime)
            writer.writeUint64(instance.timeToLiveMs)
            writer.writeNullable(instance.nonce)
            writer.writeCompact(instance.metadata.size)
            instance.metadata.toSortedMap(
                Name.comparator()
            ).forEach { (key, value) ->
                Name.write(writer, key)
                Value.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
