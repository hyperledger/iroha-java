//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptAssetEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptIdFilterAssetId
import jp.co.soramitsu.iroha2.wrapException

/**
 * AssetFilter
 *
 * Generated from 'iroha_data_model::events::data::filters::asset::AssetFilter' regular structure
 */
public data class AssetFilter(
    public val idFilter: FilterOptIdFilterAssetId,
    public val eventFilter: FilterOptAssetEventFilter
) {
    public companion object : ScaleReader<AssetFilter>, ScaleWriter<AssetFilter> {
        public override fun read(reader: ScaleCodecReader): AssetFilter = try {
            AssetFilter(
                FilterOptIdFilterAssetId.read(reader),
                FilterOptAssetEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetFilter) = try {
            FilterOptIdFilterAssetId.write(writer, instance.idFilter)
            FilterOptAssetEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
