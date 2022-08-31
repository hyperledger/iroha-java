//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId
import jp.co.soramitsu.iroha2.wrapException

/**
 * OriginFilterEventsDataEventsAssetAssetEvent
 *
 * Generated from
 * 'iroha_data_model::events::data::filters::OriginFilterEventsDataEventsAssetAssetEvent' tuple
 * structure
 */
public data class OriginFilterEventsDataEventsAssetAssetEvent(
    public val assetId: AssetId
) {
    public companion object :
        ScaleReader<OriginFilterEventsDataEventsAssetAssetEvent>,
        ScaleWriter<OriginFilterEventsDataEventsAssetAssetEvent> {
        public override fun read(reader: ScaleCodecReader): OriginFilterEventsDataEventsAssetAssetEvent =
            try {
                OriginFilterEventsDataEventsAssetAssetEvent(
                    AssetId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

        public override fun write(
            writer: ScaleCodecWriter,
            instance: OriginFilterEventsDataEventsAssetAssetEvent
        ) = try {
            AssetId.write(writer, instance.assetId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
