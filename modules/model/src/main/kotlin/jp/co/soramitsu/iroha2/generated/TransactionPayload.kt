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
import kotlin.Unit
import kotlin.collections.Map

/**
 * TransactionPayload
 *
 * Generated from 'TransactionPayload' regular structure
 */
public data class TransactionPayload(
    public val creationTimeMs: BigInteger,
    public val authority: AccountId,
    public val instructions: Executable,
    public val timeToLiveMs: NonZeroOfu64? = null,
    public val nonce: NonZeroOfu32? = null,
    public val metadata: Map<Name, Value>,
) {
    public companion object : ScaleReader<TransactionPayload>, ScaleWriter<TransactionPayload> {
        override fun read(reader: ScaleCodecReader): TransactionPayload = try {
            TransactionPayload(
                reader.readUint64(),
                AccountId.read(reader),
                Executable.read(reader),
                reader.readNullable(NonZeroOfu64) as NonZeroOfu64?,
                reader.readNullable(NonZeroOfu32) as NonZeroOfu32?,
                reader.readMap(reader.readCompactInt(), { Name.read(reader) }, { Value.read(reader) }),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: TransactionPayload): Unit = try {
            writer.writeUint64(instance.creationTimeMs)
            AccountId.write(writer, instance.authority)
            Executable.write(writer, instance.instructions)
            writer.writeNullable(NonZeroOfu64, instance.timeToLiveMs)
            writer.writeNullable(NonZeroOfu32, instance.nonce)
            writer.writeCompact(instance.metadata.size)
            instance.metadata.toSortedMap(
                Name.comparator(),
            ).forEach { (key, value) ->
                Name.write(writer, key)
                Value.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
