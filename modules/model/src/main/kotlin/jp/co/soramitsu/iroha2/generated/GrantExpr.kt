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
 * GrantExpr
 *
 * Generated from 'GrantExpr' regular structure
 */
public data class GrantExpr(
    public val `object`: EvaluatesTo<Value>,
    public val destinationId: EvaluatesTo<AccountId>,
) {
    public companion object : ScaleReader<GrantExpr>, ScaleWriter<GrantExpr> {
        override fun read(reader: ScaleCodecReader): GrantExpr = try {
            GrantExpr(
                EvaluatesTo.read(reader) as EvaluatesTo<Value>,
                EvaluatesTo.read(reader) as EvaluatesTo<AccountId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: GrantExpr): Unit = try {
            EvaluatesTo.write(writer, instance.`object`)
            EvaluatesTo.write(writer, instance.destinationId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
