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
 * AssetDefinition
 *
 * Generated from 'iroha_data_model::asset::AssetDefinition' regular structure
 */
public class AssetDefinition(
  public val valueType: AssetValueType,
  public val id: DefinitionId
) {
  public companion object : ScaleReader<AssetDefinition>, ScaleWriter<AssetDefinition> {
    public override fun read(reader: ScaleCodecReader): AssetDefinition =
        AssetDefinition(AssetValueType.read(reader),
    DefinitionId.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: AssetDefinition): Unit {
      AssetValueType.write(writer, instance.valueType)
      DefinitionId.write(writer, instance.id)
    }
  }
}
