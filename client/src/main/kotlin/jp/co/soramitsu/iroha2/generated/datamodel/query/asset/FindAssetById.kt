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
 * FindAssetById
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetById' regular structure
 */
public class FindAssetById(
  public val id: EvaluatesTo<Id>
) {
  public companion object : ScaleReader<FindAssetById>, ScaleWriter<FindAssetById> {
    public override fun read(reader: ScaleCodecReader): FindAssetById = FindAssetById(
      EvaluatesTo<Id>.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter, instance: FindAssetById): Unit {
        EvaluatesTo<Id>.write(writer, instance.id)
    }
  }
}
