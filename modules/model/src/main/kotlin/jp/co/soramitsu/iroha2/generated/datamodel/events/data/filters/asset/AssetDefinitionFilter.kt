//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOpt
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.IdFilter
import jp.co.soramitsu.iroha2.wrapException

/**
 * AssetDefinitionFilter
 *
 * Generated from 'iroha_data_model::events::data::filters::asset::AssetDefinitionFilter' regular
 * structure
 */
public data class AssetDefinitionFilter(
    public val idFilter: FilterOpt<IdFilter<DefinitionId>>,
    public val eventFilter: FilterOpt<AssetDefinitionEventFilter>
) {
    public companion object : ScaleReader<AssetDefinitionFilter>, ScaleWriter<AssetDefinitionFilter> {
        public override fun read(reader: ScaleCodecReader): AssetDefinitionFilter = try {
            AssetDefinitionFilter(
                FilterOpt.read(reader) as FilterOpt<IdFilter<DefinitionId>>,
                FilterOpt.read(reader) as FilterOpt<AssetDefinitionEventFilter>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetDefinitionFilter) = try {
            FilterOpt.write(writer, instance.idFilter)
            FilterOpt.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
