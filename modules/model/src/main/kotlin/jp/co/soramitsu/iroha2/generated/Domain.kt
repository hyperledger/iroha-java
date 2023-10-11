//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.comparator
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit
import kotlin.collections.Map

/**
 * Domain
 *
 * Generated from 'Domain' regular structure
 */
public data class Domain(
    public val id: DomainId,
    public val accounts: Map<AccountId, Account>,
    public val assetDefinitions: Map<AssetDefinitionId, AssetDefinition>,
    public val assetTotalQuantities: Map<AssetDefinitionId, NumericValue>,
    public val logo: IpfsPath? = null,
    public val metadata: Metadata,
    public val ownedBy: AccountId,
) {
    public companion object : ScaleReader<Domain>, ScaleWriter<Domain> {
        override fun read(reader: ScaleCodecReader): Domain = try {
            Domain(
                DomainId.read(reader),
                reader.readMap(reader.readCompactInt(), { AccountId.read(reader) }, { Account.read(reader) }),
                reader.readMap(
                    reader.readCompactInt(),
                    { AssetDefinitionId.read(reader) },
                    { AssetDefinition.read(reader) },
                ),
                reader.readMap(
                    reader.readCompactInt(),
                    { AssetDefinitionId.read(reader) },
                    { NumericValue.read(reader) },
                ),
                reader.readNullable(IpfsPath) as IpfsPath?,
                Metadata.read(reader),
                AccountId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: Domain): Unit = try {
            DomainId.write(writer, instance.id)
            writer.writeCompact(instance.accounts.size)
            instance.accounts.toSortedMap(
                AccountId.comparator(),
            ).forEach { (key, value) ->
                AccountId.write(writer, key)
                Account.write(writer, value)
            }
            writer.writeCompact(instance.assetDefinitions.size)
            instance.assetDefinitions.toSortedMap(
                AssetDefinitionId.comparator(),
            ).forEach { (key, value) ->
                AssetDefinitionId.write(writer, key)
                AssetDefinition.write(writer, value)
            }
            writer.writeCompact(instance.assetTotalQuantities.size)
            instance.assetTotalQuantities.toSortedMap(
                AssetDefinitionId.comparator(),
            ).forEach { (key, value) ->
                AssetDefinitionId.write(writer, key)
                NumericValue.write(writer, value)
            }
            writer.writeNullable(IpfsPath, instance.logo)
            Metadata.write(writer, instance.metadata)
            AccountId.write(writer, instance.ownedBy)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
