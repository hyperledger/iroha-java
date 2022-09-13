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
 * OriginFilterAssetEvent
 *
 * Generated from 'iroha_data_model::events::data::filters::OriginFilterAssetEvent' tuple structure
 */
public data class OriginFilterAssetEvent(
    public val assetId: AssetId
) {
    public companion object : ScaleReader<OriginFilterAssetEvent>, ScaleWriter<OriginFilterAssetEvent> {
        public override fun read(reader: ScaleCodecReader): OriginFilterAssetEvent = try {
            OriginFilterAssetEvent(
                AssetId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: OriginFilterAssetEvent) = try {
            AssetId.write(writer, instance.assetId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
