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
 * RevokeExpr
 *
 * Generated from 'RevokeExpr' regular structure
 */
public data class RevokeExpr(
    public val `object`: EvaluatesTo<Value>,
    public val destinationId: EvaluatesTo<AccountId>,
) {
    public companion object : ScaleReader<RevokeExpr>, ScaleWriter<RevokeExpr> {
        override fun read(reader: ScaleCodecReader): RevokeExpr = try {
            RevokeExpr(
                EvaluatesTo.read(reader) as EvaluatesTo<Value>,
                EvaluatesTo.read(reader) as EvaluatesTo<AccountId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: RevokeExpr): Unit = try {
            EvaluatesTo.write(writer, instance.`object`)
            EvaluatesTo.write(writer, instance.destinationId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
