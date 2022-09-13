//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptAssetEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptOriginFilterAssetEvent
import jp.co.soramitsu.iroha2.wrapException

/**
 * AssetFilter
 *
 * Generated from 'iroha_data_model::events::data::events::asset::AssetFilter' regular structure
 */
public data class AssetFilter(
    public val originFilter: FilterOptOriginFilterAssetEvent,
    public val eventFilter: FilterOptAssetEventFilter
) {
    public companion object : ScaleReader<AssetFilter>, ScaleWriter<AssetFilter> {
        public override fun read(reader: ScaleCodecReader): AssetFilter = try {
            AssetFilter(
                FilterOptOriginFilterAssetEvent.read(reader),
                FilterOptAssetEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetFilter) = try {
            FilterOptOriginFilterAssetEvent.write(writer, instance.originFilter)
            FilterOptAssetEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
