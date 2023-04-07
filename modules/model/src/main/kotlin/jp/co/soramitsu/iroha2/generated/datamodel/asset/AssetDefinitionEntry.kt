//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.wrapException

/**
 * AssetDefinitionEntry
 *
 * Generated from 'iroha_data_model::asset::AssetDefinitionEntry' regular structure
 */
public data class AssetDefinitionEntry(
    public val definition: AssetDefinition,
    public val registeredBy: AccountId
) {
    public companion object : ScaleReader<AssetDefinitionEntry>, ScaleWriter<AssetDefinitionEntry> {
        public override fun read(reader: ScaleCodecReader): AssetDefinitionEntry = try {
            AssetDefinitionEntry(
                AssetDefinition.read(reader),
                AccountId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetDefinitionEntry) = try {
            AssetDefinition.write(writer, instance.definition)
            AccountId.write(writer, instance.registeredBy)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
