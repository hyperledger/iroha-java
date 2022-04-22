//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.domain

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptDomainEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptIdFilterDomainId
import jp.co.soramitsu.iroha2.wrapException

/**
 * DomainFilter
 *
 * Generated from 'iroha_data_model::events::data::filters::domain::DomainFilter' regular structure
 */
public data class DomainFilter(
    public val idFilter: FilterOptIdFilterDomainId,
    public val eventFilter: FilterOptDomainEventFilter
) {
    public companion object : ScaleReader<DomainFilter>, ScaleWriter<DomainFilter> {
        public override fun read(reader: ScaleCodecReader): DomainFilter = try {
            DomainFilter(
                FilterOptIdFilterDomainId.read(reader),
                FilterOptDomainEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: DomainFilter) = try {
            FilterOptIdFilterDomainId.write(writer, instance.idFilter)
            FilterOptDomainEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
