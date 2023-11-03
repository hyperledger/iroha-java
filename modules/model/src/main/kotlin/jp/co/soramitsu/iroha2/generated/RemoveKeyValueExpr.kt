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
 * RemoveKeyValueExpr
 *
 * Generated from 'RemoveKeyValueExpr' regular structure
 */
public data class RemoveKeyValueExpr(
    public val objectId: EvaluatesTo<IdBox>,
    public val key: EvaluatesTo<Name>,
) {
    public companion object : ScaleReader<RemoveKeyValueExpr>, ScaleWriter<RemoveKeyValueExpr> {
        override fun read(reader: ScaleCodecReader): RemoveKeyValueExpr = try {
            RemoveKeyValueExpr(
                EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
                EvaluatesTo.read(reader) as EvaluatesTo<Name>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: RemoveKeyValueExpr): Unit = try {
            EvaluatesTo.write(writer, instance.objectId)
            EvaluatesTo.write(writer, instance.key)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
