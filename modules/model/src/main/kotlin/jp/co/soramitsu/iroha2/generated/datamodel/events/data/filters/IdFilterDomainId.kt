//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Id
import jp.co.soramitsu.iroha2.wrapException

/**
 * IdFilterDomainId
 *
 * Generated from 'iroha_data_model::events::data::filters::IdFilterDomainId' tuple structure
 */
public data class IdFilterDomainId(
    public val id: Id
) {
    public companion object : ScaleReader<IdFilterDomainId>, ScaleWriter<IdFilterDomainId> {
        public override fun read(reader: ScaleCodecReader): IdFilterDomainId = try {
            IdFilterDomainId(
                Id.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: IdFilterDomainId) = try {
            Id.write(writer, instance.id)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
