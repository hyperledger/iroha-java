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
 * SetParameterExpr
 *
 * Generated from 'SetParameterExpr' regular structure
 */
public data class SetParameterExpr(
    public val parameter: EvaluatesTo<Parameter>,
) {
    public companion object : ScaleReader<SetParameterExpr>, ScaleWriter<SetParameterExpr> {
        override fun read(reader: ScaleCodecReader): SetParameterExpr = try {
            SetParameterExpr(
                EvaluatesTo.read(reader) as EvaluatesTo<Parameter>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: SetParameterExpr): Unit = try {
            EvaluatesTo.write(writer, instance.parameter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
