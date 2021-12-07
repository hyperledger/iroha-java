//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * Asset
 *
 * Generated from 'iroha_data_model::asset::Asset' regular structure
 */
public data class Asset(
    public val id: Id,
    public val `value`: AssetValue
) {
    public companion object : ScaleReader<Asset>, ScaleWriter<Asset> {
        public override fun read(reader: ScaleCodecReader): Asset = try {
            Asset(
                Id.read(reader),
                AssetValue.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Asset) = try {
            Id.write(writer, instance.id)
            AssetValue.write(writer, instance.`value`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
