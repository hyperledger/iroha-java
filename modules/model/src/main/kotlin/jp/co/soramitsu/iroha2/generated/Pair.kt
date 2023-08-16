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

/**
 * Pair
 *
 * Generated from 'Pair' regular structure
 */
public data class Pair(
    public val leftInstruction: InstructionBox,
    public val rightInstruction: InstructionBox,
) {
    public companion object : ScaleReader<Pair>, ScaleWriter<Pair> {
        override fun read(reader: ScaleCodecReader): Pair = try {
            Pair(
                InstructionBox.read(reader),
                InstructionBox.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: Pair): Unit = try {
            InstructionBox.write(writer, instance.leftInstruction)
            InstructionBox.write(writer, instance.rightInstruction)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
