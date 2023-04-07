//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId
import jp.co.soramitsu.iroha2.wrapException

/**
 * ExecuteTriggerBox
 *
 * Generated from 'iroha_data_model::isi::ExecuteTriggerBox' regular structure
 */
public data class ExecuteTriggerBox(
    public val triggerId: TriggerId
) {
    public companion object : ScaleReader<ExecuteTriggerBox>, ScaleWriter<ExecuteTriggerBox> {
        public override fun read(reader: ScaleCodecReader): ExecuteTriggerBox = try {
            ExecuteTriggerBox(
                TriggerId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: ExecuteTriggerBox) = try {
            TriggerId.write(writer, instance.triggerId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
