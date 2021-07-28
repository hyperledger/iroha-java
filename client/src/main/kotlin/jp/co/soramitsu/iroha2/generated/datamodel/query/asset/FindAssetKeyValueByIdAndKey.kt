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
import kotlin.String
import kotlin.Unit

/**
 * FindAssetKeyValueByIdAndKey
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetKeyValueByIdAndKey' regular structure
 */
public class FindAssetKeyValueByIdAndKey(
  public val id: EvaluatesTo<Id>,
  public val key: EvaluatesTo<String>
) {
  public companion object : ScaleReader<FindAssetKeyValueByIdAndKey>,
      ScaleWriter<FindAssetKeyValueByIdAndKey> {
    public override fun read(reader: ScaleCodecReader): FindAssetKeyValueByIdAndKey =
        FindAssetKeyValueByIdAndKey(
      EvaluatesTo<Id>.read(reader),
      EvaluatesTo<String>.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter, instance: FindAssetKeyValueByIdAndKey):
        Unit {


    }
  }
}
