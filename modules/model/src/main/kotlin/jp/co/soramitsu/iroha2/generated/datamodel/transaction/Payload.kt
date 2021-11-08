//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigInteger
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map

/**
 * Payload
 *
 * Generated from 'iroha_data_model::transaction::Payload' regular structure
 */
public data class Payload(
    public val accountId: Id,
    public val instructions: List<Instruction>,
    public val creationTime: BigInteger,
    public val timeToLiveMs: BigInteger,
    public val metadata: Map<String, Value>
) {
    public companion object : ScaleReader<Payload>, ScaleWriter<Payload> {
        public override fun read(reader: ScaleCodecReader): Payload = try {
            Payload(
                Id.read(reader),
                reader.readVec(reader.readCompactInt()) { Instruction.read(reader) },
                reader.readUint64(),
                reader.readUint64(),
                reader.readMap(reader.readCompactInt(), { reader.readString() }, { Value.read(reader) }),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Payload) = try {
            Id.write(writer, instance.accountId)
            writer.writeCompact(instance.instructions.size)
            instance.instructions.forEach { value -> Instruction.write(writer, value) }
            writer.writeUint64(instance.creationTime)
            writer.writeUint64(instance.timeToLiveMs)
            writer.writeCompact(instance.metadata.size)
            instance.metadata.forEach { (key, value) ->  
                writer.writeAsList(key.toByteArray(Charsets.UTF_8))
                Value.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
