//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.account

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptEventsDataEventsAccountAccountEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptOriginFilterEventsDataEventsAccountAccountEvent
import jp.co.soramitsu.iroha2.wrapException

/**
 * AccountFilter
 *
 * Generated from 'iroha_data_model::events::data::events::account::AccountFilter' regular structure
 */
public data class AccountFilter(
    public val originFilter: FilterOptOriginFilterEventsDataEventsAccountAccountEvent,
    public val eventFilter: FilterOptEventsDataEventsAccountAccountEventFilter
) {
    public companion object : ScaleReader<AccountFilter>, ScaleWriter<AccountFilter> {
        public override fun read(reader: ScaleCodecReader): AccountFilter = try {
            AccountFilter(
                FilterOptOriginFilterEventsDataEventsAccountAccountEvent.read(reader),
                FilterOptEventsDataEventsAccountAccountEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AccountFilter) = try {
            FilterOptOriginFilterEventsDataEventsAccountAccountEvent.write(writer, instance.originFilter)
            FilterOptEventsDataEventsAccountAccountEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
