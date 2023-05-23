//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * ExecuteTriggerBox
 *
 * Generated from 'ExecuteTriggerBox' regular structure
 */
public data class ExecuteTriggerBox(
    public val triggerId: EvaluatesTo<TriggerId>
) {
    public companion object : ScaleReader<ExecuteTriggerBox>, ScaleWriter<ExecuteTriggerBox> {
        public override fun read(reader: ScaleCodecReader): ExecuteTriggerBox = try {
            ExecuteTriggerBox(
                EvaluatesTo.read(reader) as EvaluatesTo<TriggerId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: ExecuteTriggerBox) = try {
            EvaluatesTo.write(writer, instance.triggerId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
