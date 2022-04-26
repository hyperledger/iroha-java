//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.role

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptIdFilterRoleId
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptRoleEventFilter
import jp.co.soramitsu.iroha2.wrapException

/**
 * RoleFilter
 *
 * Generated from 'iroha_data_model::events::data::filters::role::RoleFilter' regular structure
 */
public data class RoleFilter(
    public val idFilter: FilterOptIdFilterRoleId,
    public val eventFilter: FilterOptRoleEventFilter
) {
    public companion object : ScaleReader<RoleFilter>, ScaleWriter<RoleFilter> {
        public override fun read(reader: ScaleCodecReader): RoleFilter = try {
            RoleFilter(
                FilterOptIdFilterRoleId.read(reader),
                FilterOptRoleEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: RoleFilter) = try {
            FilterOptIdFilterRoleId.write(writer, instance.idFilter)
            FilterOptRoleEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
