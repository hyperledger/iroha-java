//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.wrapException

/**
 * NewAssetDefinition
 *
 * Generated from 'iroha_data_model::asset::NewAssetDefinition' regular structure
 */
public data class NewAssetDefinition(
    public val id: AssetDefinitionId,
    public val valueType: AssetValueType,
    public val mintable: Mintable,
    public val metadata: Metadata
) {
    public companion object : ScaleReader<NewAssetDefinition>, ScaleWriter<NewAssetDefinition> {
        public override fun read(reader: ScaleCodecReader): NewAssetDefinition = try {
            NewAssetDefinition(
                AssetDefinitionId.read(reader),
                AssetValueType.read(reader),
                Mintable.read(reader),
                Metadata.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: NewAssetDefinition) = try {
            AssetDefinitionId.write(writer, instance.id)
            AssetValueType.write(writer, instance.valueType)
            Mintable.write(writer, instance.mintable)
            Metadata.write(writer, instance.metadata)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
