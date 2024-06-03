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
 * ExecuteTriggerExpr
 *
 * Generated from 'ExecuteTriggerExpr' regular structure
 */
public data class ExecuteTriggerExpr(
    public val triggerId: EvaluatesTo<TriggerId>,
) {
    public companion object : ScaleReader<ExecuteTriggerExpr>, ScaleWriter<ExecuteTriggerExpr> {
        override fun read(reader: ScaleCodecReader): ExecuteTriggerExpr = try {
            ExecuteTriggerExpr(
                EvaluatesTo.read(reader) as EvaluatesTo<TriggerId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: ExecuteTriggerExpr): Unit = try {
            EvaluatesTo.write(writer, instance.triggerId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
