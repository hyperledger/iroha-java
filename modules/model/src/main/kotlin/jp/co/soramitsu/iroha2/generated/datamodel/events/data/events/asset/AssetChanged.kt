//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue
import jp.co.soramitsu.iroha2.wrapException

/**
 * AssetChanged
 *
 * Generated from 'iroha_data_model::events::data::events::asset::AssetChanged' regular structure
 */
public data class AssetChanged(
    public val assetId: AssetId,
    public val amount: AssetValue
) {
    public companion object : ScaleReader<AssetChanged>, ScaleWriter<AssetChanged> {
        public override fun read(reader: ScaleCodecReader): AssetChanged = try {
            AssetChanged(
                AssetId.read(reader),
                AssetValue.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetChanged) = try {
            AssetId.write(writer, instance.assetId)
            AssetValue.write(writer, instance.amount)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
