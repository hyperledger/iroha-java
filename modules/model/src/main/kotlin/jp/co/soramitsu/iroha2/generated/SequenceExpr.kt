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
 * SequenceExpr
 *
 * Generated from 'SequenceExpr' regular structure
 */
public data class SequenceExpr(
    public val instructions: List<InstructionExpr>,
) {
    public companion object : ScaleReader<SequenceExpr>, ScaleWriter<SequenceExpr> {
        override fun read(reader: ScaleCodecReader): SequenceExpr = try {
            SequenceExpr(
                reader.readVec(reader.readCompactInt()) { InstructionExpr.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: SequenceExpr): Unit = try {
            writer.writeCompact(instance.instructions.size)
            instance.instructions.forEach { value ->
                InstructionExpr.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
