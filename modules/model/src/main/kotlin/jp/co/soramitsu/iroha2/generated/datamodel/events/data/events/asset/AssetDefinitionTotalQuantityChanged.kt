//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.NumericValue
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinitionId
import jp.co.soramitsu.iroha2.wrapException

/**
 * AssetDefinitionTotalQuantityChanged
 *
 * Generated from
 * 'iroha_data_model::events::data::events::asset::AssetDefinitionTotalQuantityChanged' regular
 * structure
 */
public data class AssetDefinitionTotalQuantityChanged(
    public val assetDefinitionId: AssetDefinitionId,
    public val totalAmount: NumericValue
) {
    public companion object :
        ScaleReader<AssetDefinitionTotalQuantityChanged>,
        ScaleWriter<AssetDefinitionTotalQuantityChanged> {
        public override fun read(reader: ScaleCodecReader): AssetDefinitionTotalQuantityChanged = try {
            AssetDefinitionTotalQuantityChanged(
                AssetDefinitionId.read(reader),
                NumericValue.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(
            writer: ScaleCodecWriter,
            instance: AssetDefinitionTotalQuantityChanged
        ) = try {
            AssetDefinitionId.write(writer, instance.assetDefinitionId)
            NumericValue.write(writer, instance.totalAmount)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
