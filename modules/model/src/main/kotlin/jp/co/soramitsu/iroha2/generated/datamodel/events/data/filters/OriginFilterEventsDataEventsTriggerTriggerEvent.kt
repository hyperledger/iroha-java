//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId
import jp.co.soramitsu.iroha2.wrapException

/**
 * OriginFilterEventsDataEventsTriggerTriggerEvent
 *
 * Generated from
 * 'iroha_data_model::events::data::filters::OriginFilterEventsDataEventsTriggerTriggerEvent' tuple
 * structure
 */
public data class OriginFilterEventsDataEventsTriggerTriggerEvent(
    public val triggerId: TriggerId
) {
    public companion object :
        ScaleReader<OriginFilterEventsDataEventsTriggerTriggerEvent>,
        ScaleWriter<OriginFilterEventsDataEventsTriggerTriggerEvent> {
        public override fun read(reader: ScaleCodecReader):
            OriginFilterEventsDataEventsTriggerTriggerEvent = try {
            OriginFilterEventsDataEventsTriggerTriggerEvent(
                TriggerId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(
            writer: ScaleCodecWriter,
            instance: OriginFilterEventsDataEventsTriggerTriggerEvent
        ) = try {
            TriggerId.write(writer, instance.triggerId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
