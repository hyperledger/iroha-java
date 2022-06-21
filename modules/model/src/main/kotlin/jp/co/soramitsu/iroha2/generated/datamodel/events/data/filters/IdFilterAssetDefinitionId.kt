//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinitionId
import jp.co.soramitsu.iroha2.wrapException

/**
 * IdFilterAssetDefinitionId
 *
 * Generated from 'iroha_data_model::events::data::filters::IdFilterAssetDefinitionId' tuple
 * structure
 */
public data class IdFilterAssetDefinitionId(
    public val assetDefinitionId: AssetDefinitionId
) {
    public companion object :
        ScaleReader<IdFilterAssetDefinitionId>,
        ScaleWriter<IdFilterAssetDefinitionId> {
        public override fun read(reader: ScaleCodecReader): IdFilterAssetDefinitionId = try {
            IdFilterAssetDefinitionId(
                AssetDefinitionId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: IdFilterAssetDefinitionId) = try {
            AssetDefinitionId.write(writer, instance.assetDefinitionId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
