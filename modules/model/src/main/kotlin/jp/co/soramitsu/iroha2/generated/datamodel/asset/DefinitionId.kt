//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Id
import jp.co.soramitsu.iroha2.wrapException

/**
 * DefinitionId
 *
 * Generated from 'iroha_data_model::asset::DefinitionId' regular structure
 */
public data class DefinitionId(
    public val name: Name,
    public val domainId: Id
) {
    public companion object : ScaleReader<DefinitionId>, ScaleWriter<DefinitionId> {
        public override fun read(reader: ScaleCodecReader): DefinitionId = try {
            DefinitionId(
                Name.read(reader),
                Id.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: DefinitionId) = try {
            Name.write(writer, instance.name)
            Id.write(writer, instance.domainId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
