//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * Pair
 *
 * Generated from 'iroha_data_model::isi::Pair' regular structure
 */
public data class Pair(
    public val leftInstruction: Instruction,
    public val rightInstruction: Instruction
) {
    public companion object : ScaleReader<Pair>, ScaleWriter<Pair> {
        public override fun read(reader: ScaleCodecReader): Pair = try {
            Pair(
                Instruction.read(reader),
                Instruction.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Pair) = try {
            Instruction.write(writer, instance.leftInstruction)
            Instruction.write(writer, instance.rightInstruction)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
