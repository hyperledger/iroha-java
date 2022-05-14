//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptAssetDefinitionEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptIdFilterAssetDefinitionId
import jp.co.soramitsu.iroha2.wrapException

/**
 * AssetDefinitionFilter
 *
 * Generated from 'iroha_data_model::events::data::filters::asset::AssetDefinitionFilter' regular
 * structure
 */
public data class AssetDefinitionFilter(
    public val idFilter: FilterOptIdFilterAssetDefinitionId,
    public val eventFilter: FilterOptAssetDefinitionEventFilter
) {
    public companion object : ScaleReader<AssetDefinitionFilter>, ScaleWriter<AssetDefinitionFilter> {
        public override fun read(reader: ScaleCodecReader): AssetDefinitionFilter = try {
            AssetDefinitionFilter(
                FilterOptIdFilterAssetDefinitionId.read(reader),
                FilterOptAssetDefinitionEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetDefinitionFilter) = try {
            FilterOptIdFilterAssetDefinitionId.write(writer, instance.idFilter)
            FilterOptAssetDefinitionEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
