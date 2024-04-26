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
 * NewParameterExpr
 *
 * Generated from 'NewParameterExpr' regular structure
 */
public data class NewParameterExpr(
    public val parameter: EvaluatesTo<Parameter>,
) {
    public companion object : ScaleReader<NewParameterExpr>, ScaleWriter<NewParameterExpr> {
        override fun read(reader: ScaleCodecReader): NewParameterExpr = try {
            NewParameterExpr(
                EvaluatesTo.read(reader) as EvaluatesTo<Parameter>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: NewParameterExpr): Unit = try {
            EvaluatesTo.write(writer, instance.parameter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
