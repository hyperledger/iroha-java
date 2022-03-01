//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOpt
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.IdFilter
import jp.co.soramitsu.iroha2.wrapException

/**
 * AssetFilter
 *
 * Generated from 'iroha_data_model::events::data::filters::asset::AssetFilter' regular structure
 */
public data class AssetFilter(
    public val idFilter: FilterOpt<IdFilter<Id>>,
    public val eventFilter: FilterOpt<AssetEventFilter>
) {
    public companion object : ScaleReader<AssetFilter>, ScaleWriter<AssetFilter> {
        public override fun read(reader: ScaleCodecReader): AssetFilter = try {
            AssetFilter(
                FilterOpt.read(reader) as FilterOpt<IdFilter<Id>>,
                FilterOpt.read(reader) as FilterOpt<AssetEventFilter>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetFilter) = try {
            FilterOpt.write(writer, instance.idFilter)
            FilterOpt.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
