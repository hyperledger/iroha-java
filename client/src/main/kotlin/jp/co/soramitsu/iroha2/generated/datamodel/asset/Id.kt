//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Unit

/**
 * Id
 *
 * Generated from 'iroha_data_model::asset::Id' regular structure
 */
public class Id(
  public val definitionId: DefinitionId,
  public val accountId: jp.co.soramitsu.iroha2.generated.datamodel.account.Id
) {
  public companion object : ScaleReader<Id>, ScaleWriter<Id> {
    public override fun read(reader: ScaleCodecReader): Id = Id(DefinitionId.read(reader),
    Id.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: Id): Unit {
      DefinitionId.write(writer, instance.definitionId)
      Id.write(writer, instance.accountId)
    }
  }
}
