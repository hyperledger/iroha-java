//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.account

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.permissions.PermissionToken
import jp.co.soramitsu.iroha2.hashMapWithSize
import jp.co.soramitsu.iroha2.hashSetWithSize
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet

/**
 * Account
 *
 * Generated from 'iroha_data_model::account::Account' regular structure
 */
public data class Account(
  public val id: Id,
  public val assets: MutableMap<jp.co.soramitsu.iroha2.generated.datamodel.asset.Id, Asset>,
  public val signatories: MutableList<PublicKey>,
  public val permissionTokens: MutableSet<PermissionToken>,
  public val signatureCheckCondition: SignatureCheckCondition,
  public val metadata: Metadata
) {
  public companion object : ScaleReader<Account>, ScaleWriter<Account> {
    public override fun read(reader: ScaleCodecReader): Account = Account(
      Id.read(reader) as Id,
      hashMapWithSize(reader.readCompactInt(),
          {jp.co.soramitsu.iroha2.generated.datamodel.asset.Id.read(reader) as
          jp.co.soramitsu.iroha2.generated.datamodel.asset.Id}, {Asset.read(reader) as Asset}),
      MutableList(reader.readCompactInt()) {PublicKey.read(reader) as PublicKey},
      hashSetWithSize(reader.readCompactInt()) {PermissionToken.read(reader) as PermissionToken},
      SignatureCheckCondition.read(reader) as SignatureCheckCondition,
      Metadata.read(reader) as Metadata,
    )

    public override fun write(writer: ScaleCodecWriter, instance: Account): Unit {
        Id.write(writer, instance.id)
        writer.writeCompact(instance.assets.size)
        instance.assets.forEach { (key, value) ->  
        	jp.co.soramitsu.iroha2.generated.datamodel.asset.Id.write(writer, key)
        	Asset.write(writer, value)
        }
        writer.writeCompact(instance.signatories.size)
        instance.signatories.forEach { value -> PublicKey.write(writer, value) }
        writer.writeCompact(instance.permissionTokens.size)
        instance.permissionTokens.forEach { value -> PermissionToken.write(writer, value) }
        SignatureCheckCondition.write(writer, instance.signatureCheckCondition)
        Metadata.write(writer, instance.metadata)
    }
  }
}
