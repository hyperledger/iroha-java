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
 * PairExpr
 *
 * Generated from 'PairExpr' regular structure
 */
public data class PairExpr(
    public val leftInstruction: InstructionExpr,
    public val rightInstruction: InstructionExpr,
) {
    public companion object : ScaleReader<PairExpr>, ScaleWriter<PairExpr> {
        override fun read(reader: ScaleCodecReader): PairExpr = try {
            PairExpr(
                InstructionExpr.read(reader),
                InstructionExpr.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: PairExpr): Unit = try {
            InstructionExpr.write(writer, instance.leftInstruction)
            InstructionExpr.write(writer, instance.rightInstruction)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
