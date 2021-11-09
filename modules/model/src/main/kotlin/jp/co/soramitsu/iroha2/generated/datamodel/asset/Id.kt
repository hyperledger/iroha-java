//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * Id
 *
 * Generated from 'iroha_data_model::asset::Id' regular structure
 */
public data class Id(
    public val definitionId: DefinitionId,
    public val accountId: jp.co.soramitsu.iroha2.generated.datamodel.account.Id
) {
    public companion object : ScaleReader<Id>, ScaleWriter<Id> {
        public override fun read(reader: ScaleCodecReader): Id = try {
            Id(
                DefinitionId.read(reader),
                jp.co.soramitsu.iroha2.generated.datamodel.account.Id.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Id) = try {
            DefinitionId.write(writer, instance.definitionId)
            jp.co.soramitsu.iroha2.generated.datamodel.account.Id.write(writer, instance.accountId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
