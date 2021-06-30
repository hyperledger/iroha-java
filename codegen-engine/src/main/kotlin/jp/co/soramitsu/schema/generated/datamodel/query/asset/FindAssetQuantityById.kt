// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.query.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo
import kotlin.Unit

/**
 * FindAssetQuantityById
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetQuantityById' regular structure
 */
public class FindAssetQuantityById(
  private val id: EvaluatesTo
) : ScaleReader<FindAssetQuantityById>, ScaleWriter<FindAssetQuantityById> {
  public override fun read(reader: ScaleCodecReader): FindAssetQuantityById =
      FindAssetQuantityById(id.read(reader))

  public override fun write(writer: ScaleCodecWriter, instance: FindAssetQuantityById): Unit {
    id.write(writer, instance.id)
  }
}
