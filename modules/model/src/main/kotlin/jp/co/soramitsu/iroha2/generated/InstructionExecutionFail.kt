//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String
import kotlin.Unit

/**
 * InstructionExecutionFail
 *
 * Generated from 'InstructionExecutionFail' regular structure
 */
public data class InstructionExecutionFail(
    public val instruction: InstructionExpr,
    public val reason: String,
) {
    public companion object :
        ScaleReader<InstructionExecutionFail>,
        ScaleWriter<InstructionExecutionFail> {
        override fun read(reader: ScaleCodecReader): InstructionExecutionFail = try {
            InstructionExecutionFail(
                InstructionExpr.read(reader),
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: InstructionExecutionFail): Unit = try {
            InstructionExpr.write(writer, instance.instruction)
            writer.writeAsList(instance.reason.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
