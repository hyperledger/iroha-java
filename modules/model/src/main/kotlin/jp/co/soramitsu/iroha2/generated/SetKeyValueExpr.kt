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
 * SetKeyValueExpr
 *
 * Generated from 'SetKeyValueExpr' regular structure
 */
public data class SetKeyValueExpr(
    public val objectId: EvaluatesTo<IdBox>,
    public val key: EvaluatesTo<Name>,
    public val `value`: EvaluatesTo<Value>,
) {
    public companion object : ScaleReader<SetKeyValueExpr>, ScaleWriter<SetKeyValueExpr> {
        override fun read(reader: ScaleCodecReader): SetKeyValueExpr = try {
            SetKeyValueExpr(
                EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
                EvaluatesTo.read(reader) as EvaluatesTo<Name>,
                EvaluatesTo.read(reader) as EvaluatesTo<Value>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: SetKeyValueExpr): Unit = try {
            EvaluatesTo.write(writer, instance.objectId)
            EvaluatesTo.write(writer, instance.key)
            EvaluatesTo.write(writer, instance.`value`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
