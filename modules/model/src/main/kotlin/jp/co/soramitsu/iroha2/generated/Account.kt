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
import kotlin.collections.List
import kotlin.collections.Map

/**
 * Account
 *
 * Generated from 'Account' regular structure
 */
public data class Account(
    public val id: AccountId,
    public val assets: Map<AssetId, Asset>,
    public val signatories: List<PublicKey>,
    public val signatureCheckCondition: SignatureCheckCondition,
    public val metadata: Metadata,
    public val roles: List<RoleId>
) {
    public companion object : ScaleReader<Account>, ScaleWriter<Account> {
        public override fun read(reader: ScaleCodecReader): Account = try {
            Account(
                AccountId.read(reader),
                reader.readMap(reader.readCompactInt(), { AssetId.read(reader) }, { Asset.read(reader) }),
                reader.readVec(reader.readCompactInt()) { PublicKey.read(reader) },
                SignatureCheckCondition.read(reader),
                Metadata.read(reader),
                reader.readVec(reader.readCompactInt()) { RoleId.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Account) = try {
            AccountId.write(writer, instance.id)
            writer.writeCompact(instance.assets.size)
            instance.assets.toSortedMap(
                AssetId.comparator()
            ).forEach { (key, value) ->
                AssetId.write(writer, key)
                Asset.write(writer, value)
            }
            writer.writeCompact(instance.signatories.size)
            instance.signatories.forEach { value ->
                PublicKey.write(writer, value)
            }
            SignatureCheckCondition.write(writer, instance.signatureCheckCondition)
            Metadata.write(writer, instance.metadata)
            writer.writeCompact(instance.roles.size)
            instance.roles.forEach { value ->
                RoleId.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
