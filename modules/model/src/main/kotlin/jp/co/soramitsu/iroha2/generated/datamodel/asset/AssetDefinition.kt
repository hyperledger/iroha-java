//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Boolean

/**
 * AssetDefinition
 *
 * Generated from 'iroha_data_model::asset::AssetDefinition' regular structure
 */
public data class AssetDefinition(
    public val valueType: AssetValueType,
    public val id: DefinitionId,
    public val metadata: Metadata,
    public val mintable: Boolean
) {
    public companion object : ScaleReader<AssetDefinition>, ScaleWriter<AssetDefinition> {
        public override fun read(reader: ScaleCodecReader): AssetDefinition = try {
            AssetDefinition(
                AssetValueType.read(reader),
                DefinitionId.read(reader),
                Metadata.read(reader),
                reader.readBoolean(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetDefinition) = try {
            AssetValueType.write(writer, instance.valueType)
            DefinitionId.write(writer, instance.id)
            Metadata.write(writer, instance.metadata)
            if (instance.mintable) { writer.directWrite(1) } else { writer.directWrite(0) }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
