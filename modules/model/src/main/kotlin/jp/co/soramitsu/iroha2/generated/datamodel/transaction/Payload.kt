//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.hashMapWithSize
import jp.co.soramitsu.iroha2.readBit64
import jp.co.soramitsu.iroha2.wrapException
import jp.co.soramitsu.iroha2.writeBit64
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
                List(reader.readCompactInt()) { Instruction.read(reader) },
                readBit64(reader),
                readBit64(reader),
                hashMapWithSize(reader.readCompactInt(), { reader.readString() }, { Value.read(reader) }),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Payload) = try {
            Id.write(writer, instance.accountId)
            writer.writeCompact(instance.instructions.size)
            instance.instructions.forEach { value -> Instruction.write(writer, value) }
            writeBit64(writer, instance.creationTime)
            writeBit64(writer, instance.timeToLiveMs)
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
