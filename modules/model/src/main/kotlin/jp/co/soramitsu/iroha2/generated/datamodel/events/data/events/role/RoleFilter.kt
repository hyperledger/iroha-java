//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.role

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptEventsDataEventsRoleRoleEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptOriginFilterEventsDataEventsRoleRoleEvent
import jp.co.soramitsu.iroha2.wrapException

/**
 * RoleFilter
 *
 * Generated from 'iroha_data_model::events::data::events::role::RoleFilter' regular structure
 */
public data class RoleFilter(
    public val originFilter: FilterOptOriginFilterEventsDataEventsRoleRoleEvent,
    public val eventFilter: FilterOptEventsDataEventsRoleRoleEventFilter
) {
    public companion object : ScaleReader<RoleFilter>, ScaleWriter<RoleFilter> {
        public override fun read(reader: ScaleCodecReader): RoleFilter = try {
            RoleFilter(
                FilterOptOriginFilterEventsDataEventsRoleRoleEvent.read(reader),
                FilterOptEventsDataEventsRoleRoleEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: RoleFilter) = try {
            FilterOptOriginFilterEventsDataEventsRoleRoleEvent.write(writer, instance.originFilter)
            FilterOptEventsDataEventsRoleRoleEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
