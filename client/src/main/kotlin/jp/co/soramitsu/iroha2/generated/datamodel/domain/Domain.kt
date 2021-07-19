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
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableMap

/**
 * Domain
 *
 * Generated from 'iroha_data_model::domain::Domain' regular structure
 */
public class Domain(
  public val name: String,
  public val accounts: MutableMap<Id, Account>,
  public val assetDefinitions: MutableMap<DefinitionId, AssetDefinitionEntry>
) {
  public companion object : ScaleReader<Domain>, ScaleWriter<Domain> {
    public override fun read(reader: ScaleCodecReader): Domain =
        Domain(jp.co.soramitsu.iroha2.scale.StringReader.read(reader),
    jp.co.soramitsu.iroha2.scale.MapReader(jp.co.soramitsu.iroha2.generated.datamodel.account.Id,
        jp.co.soramitsu.iroha2.generated.datamodel.account.Account).read(reader),
    jp.co.soramitsu.iroha2.scale.MapReader(jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId,
        jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinitionEntry).read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: Domain): Unit {
      jp.co.soramitsu.iroha2.scale.StringWriter.write(writer, instance.name)
      jp.co.soramitsu.iroha2.scale.MapWriter(jp.co.soramitsu.iroha2.generated.datamodel.account.Id,
          jp.co.soramitsu.iroha2.generated.datamodel.account.Account).write(writer,
          instance.accounts)
      jp.co.soramitsu.iroha2.scale.MapWriter(jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId,
          jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinitionEntry).write(writer,
          instance.assetDefinitions)
    }
  }
}
