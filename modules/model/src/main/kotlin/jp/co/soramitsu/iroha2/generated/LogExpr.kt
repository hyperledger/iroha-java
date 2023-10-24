//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String
import kotlin.Unit

/**
 * LogExpr
 *
 * Generated from 'LogExpr' regular structure
 */
public data class LogExpr(
    public val level: EvaluatesTo<Level>,
    public val msg: EvaluatesTo<String>,
) {
    public companion object : ScaleReader<LogExpr>, ScaleWriter<LogExpr> {
        override fun read(reader: ScaleCodecReader): LogExpr = try {
            LogExpr(
                EvaluatesTo.read(reader) as EvaluatesTo<Level>,
                EvaluatesTo.read(reader) as EvaluatesTo<String>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: LogExpr): Unit = try {
            EvaluatesTo.write(writer, instance.level)
            EvaluatesTo.write(writer, instance.msg)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
