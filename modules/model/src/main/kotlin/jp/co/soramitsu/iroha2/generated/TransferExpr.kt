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
 * TransferExpr
 *
 * Generated from 'TransferExpr' regular structure
 */
public data class TransferExpr(
    public val sourceId: EvaluatesTo<IdBox>,
    public val `object`: EvaluatesTo<Value>,
    public val destinationId: EvaluatesTo<IdBox>,
) {
    public companion object : ScaleReader<TransferExpr>, ScaleWriter<TransferExpr> {
        override fun read(reader: ScaleCodecReader): TransferExpr = try {
            TransferExpr(
                EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
                EvaluatesTo.read(reader) as EvaluatesTo<Value>,
                EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: TransferExpr): Unit = try {
            EvaluatesTo.write(writer, instance.sourceId)
            EvaluatesTo.write(writer, instance.`object`)
            EvaluatesTo.write(writer, instance.destinationId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
