//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Boolean
import kotlin.Unit

/**
 * Conditional
 *
 * Generated from 'Conditional' regular structure
 */
public data class Conditional(
    public val condition: EvaluatesTo<Boolean>,
    public val then: InstructionBox,
    public val otherwise: InstructionBox? = null,
) {
    public companion object : ScaleReader<Conditional>, ScaleWriter<Conditional> {
        override fun read(reader: ScaleCodecReader): Conditional = try {
            Conditional(
                EvaluatesTo.read(reader) as EvaluatesTo<Boolean>,
                InstructionBox.read(reader),
                reader.readNullable(InstructionBox) as InstructionBox?,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: Conditional): Unit = try {
            EvaluatesTo.write(writer, instance.condition)
            InstructionBox.write(writer, instance.then)
            writer.writeNullable(InstructionBox, instance.otherwise)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
