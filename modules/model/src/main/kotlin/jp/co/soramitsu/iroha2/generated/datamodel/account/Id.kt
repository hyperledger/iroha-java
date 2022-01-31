//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.account

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.wrapException

/**
 * Id
 *
 * Generated from 'iroha_data_model::account::Id' regular structure
 */
public data class Id(
    public val name: Name,
    public val domainId: jp.co.soramitsu.iroha2.generated.datamodel.domain.Id
) {
    public companion object : ScaleReader<Id>, ScaleWriter<Id> {
        public override fun read(reader: ScaleCodecReader): Id = try {
            Id(
                Name.read(reader),
                jp.co.soramitsu.iroha2.generated.datamodel.domain.Id.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Id) = try {
            Name.write(writer, instance.name)
            jp.co.soramitsu.iroha2.generated.datamodel.domain.Id.write(writer, instance.domainId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
