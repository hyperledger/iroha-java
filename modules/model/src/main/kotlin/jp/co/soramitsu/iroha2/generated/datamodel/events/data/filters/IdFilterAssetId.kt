//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id
import jp.co.soramitsu.iroha2.wrapException

/**
 * IdFilterAssetId
 *
 * Generated from 'iroha_data_model::events::data::filters::IdFilterAssetId' tuple structure
 */
public data class IdFilterAssetId(
    public val id: Id
) {
    public companion object : ScaleReader<IdFilterAssetId>, ScaleWriter<IdFilterAssetId> {
        public override fun read(reader: ScaleCodecReader): IdFilterAssetId = try {
            IdFilterAssetId(
                Id.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: IdFilterAssetId) = try {
            Id.write(writer, instance.id)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
