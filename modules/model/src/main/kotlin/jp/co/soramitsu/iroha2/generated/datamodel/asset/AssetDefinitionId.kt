//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId
import jp.co.soramitsu.iroha2.wrapException

/**
 * AssetDefinitionId
 *
 * Generated from 'iroha_data_model::asset::AssetDefinitionId' regular structure
 */
public data class AssetDefinitionId(
    public val name: Name,
    public val domainId: DomainId
) {
    public companion object : ScaleReader<AssetDefinitionId>, ScaleWriter<AssetDefinitionId> {
        public override fun read(reader: ScaleCodecReader): AssetDefinitionId = try {
            AssetDefinitionId(
                Name.read(reader),
                DomainId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetDefinitionId) = try {
            Name.write(writer, instance.name)
            DomainId.write(writer, instance.domainId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
