//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.role

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOpt
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.IdFilter
import jp.co.soramitsu.iroha2.generated.datamodel.role.Id
import jp.co.soramitsu.iroha2.wrapException

/**
 * RoleFilter
 *
 * Generated from 'iroha_data_model::events::data::filters::role::RoleFilter' regular structure
 */
public data class RoleFilter(
    public val idFilter: FilterOpt<IdFilter<Id>>,
    public val eventFilter: FilterOpt<RoleEventFilter>
) {
    public companion object : ScaleReader<RoleFilter>, ScaleWriter<RoleFilter> {
        public override fun read(reader: ScaleCodecReader): RoleFilter = try {
            RoleFilter(
                FilterOpt.read(reader) as FilterOpt<IdFilter<Id>>,
                FilterOpt.read(reader) as FilterOpt<RoleEventFilter>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: RoleFilter) = try {
            FilterOpt.write(writer, instance.idFilter)
            FilterOpt.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
