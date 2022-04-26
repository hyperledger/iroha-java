//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.trigger

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptIdFilterTriggerId
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptTriggerEventFilter
import jp.co.soramitsu.iroha2.wrapException

/**
 * TriggerFilter
 *
 * Generated from 'iroha_data_model::events::data::filters::trigger::TriggerFilter' regular
 * structure
 */
public data class TriggerFilter(
    public val idFilter: FilterOptIdFilterTriggerId,
    public val eventFilter: FilterOptTriggerEventFilter
) {
    public companion object : ScaleReader<TriggerFilter>, ScaleWriter<TriggerFilter> {
        public override fun read(reader: ScaleCodecReader): TriggerFilter = try {
            TriggerFilter(
                FilterOptIdFilterTriggerId.read(reader),
                FilterOptTriggerEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: TriggerFilter) = try {
            FilterOptIdFilterTriggerId.write(writer, instance.idFilter)
            FilterOptTriggerEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
