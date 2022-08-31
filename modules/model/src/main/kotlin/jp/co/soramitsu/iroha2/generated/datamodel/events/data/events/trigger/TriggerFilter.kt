//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.trigger

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptEventsDataEventsTriggerTriggerEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptOriginFilterEventsDataEventsTriggerTriggerEvent
import jp.co.soramitsu.iroha2.wrapException

/**
 * TriggerFilter
 *
 * Generated from 'iroha_data_model::events::data::events::trigger::TriggerFilter' regular structure
 */
public data class TriggerFilter(
    public val originFilter: FilterOptOriginFilterEventsDataEventsTriggerTriggerEvent,
    public val eventFilter: FilterOptEventsDataEventsTriggerTriggerEventFilter
) {
    public companion object : ScaleReader<TriggerFilter>, ScaleWriter<TriggerFilter> {
        public override fun read(reader: ScaleCodecReader): TriggerFilter = try {
            TriggerFilter(
                FilterOptOriginFilterEventsDataEventsTriggerTriggerEvent.read(reader),
                FilterOptEventsDataEventsTriggerTriggerEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: TriggerFilter) = try {
            FilterOptOriginFilterEventsDataEventsTriggerTriggerEvent.write(writer, instance.originFilter)
            FilterOptEventsDataEventsTriggerTriggerEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
