//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.domain

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptEventsDataEventsDomainDomainEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptOriginFilterEventsDataEventsDomainDomainEvent
import jp.co.soramitsu.iroha2.wrapException

/**
 * DomainFilter
 *
 * Generated from 'iroha_data_model::events::data::events::domain::DomainFilter' regular structure
 */
public data class DomainFilter(
    public val originFilter: FilterOptOriginFilterEventsDataEventsDomainDomainEvent,
    public val eventFilter: FilterOptEventsDataEventsDomainDomainEventFilter
) {
    public companion object : ScaleReader<DomainFilter>, ScaleWriter<DomainFilter> {
        public override fun read(reader: ScaleCodecReader): DomainFilter = try {
            DomainFilter(
                FilterOptOriginFilterEventsDataEventsDomainDomainEvent.read(reader),
                FilterOptEventsDataEventsDomainDomainEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: DomainFilter) = try {
            FilterOptOriginFilterEventsDataEventsDomainDomainEvent.write(writer, instance.originFilter)
            FilterOptEventsDataEventsDomainDomainEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
