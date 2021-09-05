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
import jp.co.soramitsu.iroha2.writeBit64
import kotlin.String
import kotlin.ULong
import kotlin.collections.MutableList
import kotlin.collections.MutableMap

/**
 * Payload
 *
 * Generated from 'iroha_data_model::transaction::Payload' regular structure
 */
public data class Payload(
    public val accountId: Id,
    public val instructions: MutableList<Instruction>,
    public val creationTime: ULong,
    public val timeToLiveMs: ULong,
    public val metadata: MutableMap<String, Value>
) {
    public companion object : ScaleReader<Payload>, ScaleWriter<Payload> {
        public override fun read(reader: ScaleCodecReader): Payload = Payload(
            Id.read(reader),
            MutableList(reader.readCompactInt()) { Instruction.read(reader) },
            readBit64(reader).toULong(),
            readBit64(reader).toULong(),
            hashMapWithSize(reader.readCompactInt(), { reader.readString() }, { Value.read(reader) }),
        )

        public override fun write(writer: ScaleCodecWriter, instance: Payload) {
            Id.write(writer, instance.accountId)
            writer.writeCompact(instance.instructions.size)
            instance.instructions.forEach { value -> Instruction.write(writer, value) }
            writeBit64(writer, instance.creationTime.toLong())
            writeBit64(writer, instance.timeToLiveMs.toLong())
            writer.writeCompact(instance.metadata.size)
            instance.metadata.forEach { (key, value) ->
                writer.writeAsList(key.toByteArray(Charsets.UTF_8))
                Value.write(writer, value)
            }
        }
    }
}
