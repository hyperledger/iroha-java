//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.wrapException

/**
 * DefinitionId
 *
 * Generated from 'iroha_data_model::asset::DefinitionId' regular structure
 */
public data class DefinitionId(
    public val name: Name,
    public val domainId: DomainId
) {
    public companion object : ScaleReader<DefinitionId>, ScaleWriter<DefinitionId> {
        public override fun read(reader: ScaleCodecReader): DefinitionId = try {
            DefinitionId(
                Name.read(reader),
                DomainId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: DefinitionId) = try {
            Name.write(writer, instance.name)
            DomainId.write(writer, instance.domainId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
