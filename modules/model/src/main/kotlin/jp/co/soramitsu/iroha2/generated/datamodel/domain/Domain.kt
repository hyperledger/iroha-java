//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.domain

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinitionEntry
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.hashMapWithSize
import kotlin.String
import kotlin.collections.MutableMap

/**
 * Domain
 *
 * Generated from 'iroha_data_model::domain::Domain' regular structure
 */
public data class Domain(
    public val name: String,
    public val accounts: MutableMap<Id, Account>,
    public val assetDefinitions: MutableMap<DefinitionId, AssetDefinitionEntry>
) {
    public companion object : ScaleReader<Domain>, ScaleWriter<Domain> {
        public override fun read(reader: ScaleCodecReader): Domain = Domain(
            reader.readString(),
            hashMapWithSize(
                reader.readCompactInt(), { Id.read(reader) as Id },
                {
                    Account.read(reader) as
                        Account
                }
            ),
            hashMapWithSize(
                reader.readCompactInt(), { DefinitionId.read(reader) as DefinitionId },
                { AssetDefinitionEntry.read(reader) as AssetDefinitionEntry }
            ),
        )

        public override fun write(writer: ScaleCodecWriter, instance: Domain) {
            writer.writeAsList(instance.name.toByteArray(Charsets.UTF_8))
            writer.writeCompact(instance.accounts.size)
            instance.accounts.forEach { (key, value) ->
                Id.write(writer, key)
                Account.write(writer, value)
            }
            writer.writeCompact(instance.assetDefinitions.size)
            instance.assetDefinitions.forEach { (key, value) ->
                DefinitionId.write(writer, key)
                AssetDefinitionEntry.write(writer, value)
            }
        }
    }
}