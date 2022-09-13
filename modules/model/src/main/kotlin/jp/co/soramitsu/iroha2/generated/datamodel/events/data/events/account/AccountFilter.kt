//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.account

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptAccountEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptOriginFilterAccountEvent
import jp.co.soramitsu.iroha2.wrapException

/**
 * AccountFilter
 *
 * Generated from 'iroha_data_model::events::data::events::account::AccountFilter' regular structure
 */
public data class AccountFilter(
    public val originFilter: FilterOptOriginFilterAccountEvent,
    public val eventFilter: FilterOptAccountEventFilter
) {
    public companion object : ScaleReader<AccountFilter>, ScaleWriter<AccountFilter> {
        public override fun read(reader: ScaleCodecReader): AccountFilter = try {
            AccountFilter(
                FilterOptOriginFilterAccountEvent.read(reader),
                FilterOptAccountEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AccountFilter) = try {
            FilterOptOriginFilterAccountEvent.write(writer, instance.originFilter)
            FilterOptAccountEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
