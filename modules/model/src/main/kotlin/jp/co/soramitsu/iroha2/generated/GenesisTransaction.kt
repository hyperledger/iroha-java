//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List

/**
 * GenesisTransaction
 *
 * Generated from 'GenesisTransaction' regular structure
 */
public data class GenesisTransaction(
    public val isi: List<InstructionBox>
) {
    public companion object : ScaleReader<GenesisTransaction>, ScaleWriter<GenesisTransaction> {
        public override fun read(reader: ScaleCodecReader): GenesisTransaction = try {
            GenesisTransaction(
                reader.readVec(reader.readCompactInt()) { InstructionBox.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: GenesisTransaction) = try {
            writer.writeCompact(instance.isi.size)
            instance.isi.forEach { value ->
                InstructionBox.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
