//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String

/**
 * DefinitionId
 *
 * Generated from 'iroha_data_model::asset::DefinitionId' regular structure
 */
public data class DefinitionId(
    public val name: String,
    public val domainName: String
) {
    public companion object : ScaleReader<DefinitionId>, ScaleWriter<DefinitionId> {
        public override fun read(reader: ScaleCodecReader): DefinitionId = try {
            DefinitionId(
                reader.readString(),
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: DefinitionId) = try {
            writer.writeAsList(instance.name.toByteArray(Charsets.UTF_8))
            writer.writeAsList(instance.domainName.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
