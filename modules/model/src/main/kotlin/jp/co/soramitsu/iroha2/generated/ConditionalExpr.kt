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
 * ConditionalExpr
 *
 * Generated from 'ConditionalExpr' regular structure
 */
public data class ConditionalExpr(
    public val condition: EvaluatesTo<Boolean>,
    public val then: InstructionExpr,
    public val otherwise: InstructionExpr? = null,
) {
    public companion object : ScaleReader<ConditionalExpr>, ScaleWriter<ConditionalExpr> {
        override fun read(reader: ScaleCodecReader): ConditionalExpr = try {
            ConditionalExpr(
                EvaluatesTo.read(reader) as EvaluatesTo<Boolean>,
                InstructionExpr.read(reader),
                reader.readNullable(InstructionExpr) as InstructionExpr?,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: ConditionalExpr): Unit = try {
            EvaluatesTo.write(writer, instance.condition)
            InstructionExpr.write(writer, instance.then)
            writer.writeNullable(InstructionExpr, instance.otherwise)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
