//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.trigger

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOpt
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.IdFilter
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id
import jp.co.soramitsu.iroha2.wrapException

/**
 * TriggerFilter
 *
 * Generated from 'iroha_data_model::events::data::filters::trigger::TriggerFilter' regular
 * structure
 */
public data class TriggerFilter(
    public val idFilter: FilterOpt<IdFilter<Id>>,
    public val eventFilter: FilterOpt<TriggerEventFilter>
) {
    public companion object : ScaleReader<TriggerFilter>, ScaleWriter<TriggerFilter> {
        public override fun read(reader: ScaleCodecReader): TriggerFilter = try {
            TriggerFilter(
                FilterOpt.read(reader) as FilterOpt<IdFilter<Id>>,
                FilterOpt.read(reader) as FilterOpt<TriggerEventFilter>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: TriggerFilter) = try {
            FilterOpt.write(writer, instance.idFilter)
            FilterOpt.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
