//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.Unit

/**
 * FindAssetQuantityById
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetQuantityById' regular structure
 */
public class FindAssetQuantityById(
  public val id: EvaluatesTo<Id>
) {
  public companion object : ScaleReader<FindAssetQuantityById>, ScaleWriter<FindAssetQuantityById> {
    public override fun read(reader: ScaleCodecReader): FindAssetQuantityById =
        FindAssetQuantityById(Id.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: FindAssetQuantityById): Unit {
      Id.write(writer, instance.id)
    }
  }
}
