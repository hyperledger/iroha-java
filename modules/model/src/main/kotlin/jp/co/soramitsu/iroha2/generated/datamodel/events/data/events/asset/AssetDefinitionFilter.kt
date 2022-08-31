//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptEventsDataEventsAssetAssetDefinitionEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptOriginFilterEventsDataEventsAssetAssetDefinitionEvent
import jp.co.soramitsu.iroha2.wrapException

/**
 * AssetDefinitionFilter
 *
 * Generated from 'iroha_data_model::events::data::events::asset::AssetDefinitionFilter' regular
 * structure
 */
public data class AssetDefinitionFilter(
    public val originFilter: FilterOptOriginFilterEventsDataEventsAssetAssetDefinitionEvent,
    public val eventFilter: FilterOptEventsDataEventsAssetAssetDefinitionEventFilter
) {
    public companion object : ScaleReader<AssetDefinitionFilter>, ScaleWriter<AssetDefinitionFilter> {
        public override fun read(reader: ScaleCodecReader): AssetDefinitionFilter = try {
            AssetDefinitionFilter(
                FilterOptOriginFilterEventsDataEventsAssetAssetDefinitionEvent.read(reader),
                FilterOptEventsDataEventsAssetAssetDefinitionEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetDefinitionFilter) = try {
            FilterOptOriginFilterEventsDataEventsAssetAssetDefinitionEvent.write(
                writer,
                instance.originFilter
            )
            FilterOptEventsDataEventsAssetAssetDefinitionEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
