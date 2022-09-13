//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.trigger

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptOriginFilterTriggerEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptTriggerEventFilter
import jp.co.soramitsu.iroha2.wrapException

/**
 * TriggerFilter
 *
 * Generated from 'iroha_data_model::events::data::events::trigger::TriggerFilter' regular structure
 */
public data class TriggerFilter(
    public val originFilter: FilterOptOriginFilterTriggerEvent,
    public val eventFilter: FilterOptTriggerEventFilter
) {
    public companion object : ScaleReader<TriggerFilter>, ScaleWriter<TriggerFilter> {
        public override fun read(reader: ScaleCodecReader): TriggerFilter = try {
            TriggerFilter(
                FilterOptOriginFilterTriggerEvent.read(reader),
                FilterOptTriggerEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: TriggerFilter) = try {
            FilterOptOriginFilterTriggerEvent.write(writer, instance.originFilter)
            FilterOptTriggerEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
