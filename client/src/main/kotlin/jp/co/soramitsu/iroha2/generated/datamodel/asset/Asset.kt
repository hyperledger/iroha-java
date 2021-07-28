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
 * Asset
 *
 * Generated from 'iroha_data_model::asset::Asset' regular structure
 */
public class Asset(
  public val id: Id,
  public val `value`: AssetValue
) {
  public companion object : ScaleReader<Asset>, ScaleWriter<Asset> {
    public override fun read(reader: ScaleCodecReader): Asset = Asset(
      Id.read(reader),
      AssetValue.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter, instance: Asset): Unit {
        Id.write(writer, instance.id)
        AssetValue.write(writer, instance.value)
    }
  }
}
