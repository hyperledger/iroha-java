//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Unit

/**
 * FindAllAssetsDefinitions
 *
 * Generated from 'iroha_data_model::query::asset::FindAllAssetsDefinitions' regular structure
 */
public class FindAllAssetsDefinitions {
  public companion object : ScaleReader<FindAllAssetsDefinitions>,
      ScaleWriter<FindAllAssetsDefinitions> {
    public override fun read(reader: ScaleCodecReader): FindAllAssetsDefinitions =
        FindAllAssetsDefinitions()

    public override fun write(writer: ScaleCodecWriter, instance: FindAllAssetsDefinitions): Unit {
    }
  }
}
