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
 * RegisterExpr
 *
 * Generated from 'RegisterExpr' regular structure
 */
public data class RegisterExpr(
    public val `object`: EvaluatesTo<RegistrableBox>,
) {
    public companion object : ScaleReader<RegisterExpr>, ScaleWriter<RegisterExpr> {
        override fun read(reader: ScaleCodecReader): RegisterExpr = try {
            RegisterExpr(
                EvaluatesTo.read(reader) as EvaluatesTo<RegistrableBox>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: RegisterExpr): Unit = try {
            EvaluatesTo.write(writer, instance.`object`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
