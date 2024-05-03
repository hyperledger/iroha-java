//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit
import kotlin.collections.List

/**
 * GenesisTransactionBuilder
 *
 * Generated from 'GenesisTransactionBuilder' regular structure
 */
public data class GenesisTransactionBuilder(
    public val isi: List<InstructionBox>,
) {
    public companion object :
        ScaleReader<GenesisTransactionBuilder>,
        ScaleWriter<GenesisTransactionBuilder> {
        override fun read(reader: ScaleCodecReader): GenesisTransactionBuilder = try {
            GenesisTransactionBuilder(
                reader.readVec(reader.readCompactInt()) { InstructionBox.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: GenesisTransactionBuilder): Unit = try {
            writer.writeCompact(instance.isi.size)
            instance.isi.forEach { value ->
                InstructionBox.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
