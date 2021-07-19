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
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet

/**
 * Account
 *
 * Generated from 'iroha_data_model::account::Account' regular structure
 */
public class Account(
  public val id: Id,
  public val assets: MutableMap<jp.co.soramitsu.iroha2.generated.datamodel.asset.Id, Asset>,
  public val signatories: MutableList<PublicKey>,
  public val permissionTokens: MutableSet<PermissionToken>,
  public val signatureCheckCondition: SignatureCheckCondition,
  public val metadata: Metadata
) {
  public companion object : ScaleReader<Account>, ScaleWriter<Account> {
    public override fun read(reader: ScaleCodecReader): Account = Account(Id.read(reader),
    jp.co.soramitsu.iroha2.scale.MapReader(jp.co.soramitsu.iroha2.generated.datamodel.asset.Id,
        jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset).read(reader),
    io.emeraldpay.polkaj.scale.reader.ListReader(jp.co.soramitsu.iroha2.generated.crypto.PublicKey).read(reader),
    jp.co.soramitsu.iroha2.scale.SetReader(jp.co.soramitsu.iroha2.generated.datamodel.permissions.PermissionToken).read(reader),
    SignatureCheckCondition.read(reader),
    Metadata.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: Account): Unit {
      Id.write(writer, instance.id)
      jp.co.soramitsu.iroha2.scale.MapWriter(jp.co.soramitsu.iroha2.generated.datamodel.asset.Id,
          jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset).write(writer, instance.assets)
      io.emeraldpay.polkaj.scale.writer.ListWriter(jp.co.soramitsu.iroha2.generated.crypto.PublicKey).write(writer,
          instance.signatories)
      jp.co.soramitsu.iroha2.scale.SetWriter(jp.co.soramitsu.iroha2.generated.datamodel.permissions.PermissionToken).write(writer,
          instance.permissionTokens)
      SignatureCheckCondition.write(writer, instance.signatureCheckCondition)
      Metadata.write(writer, instance.metadata)
    }
  }
}
