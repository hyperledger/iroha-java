// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.query.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo
import kotlin.Unit

/**
 * FindAssetKeyValueByIdAndKey
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetKeyValueByIdAndKey' regular structure
 */
public class FindAssetKeyValueByIdAndKey(
  private val id: EvaluatesTo,
  private val key: EvaluatesTo
) {
  public companion object CODEC : ScaleReader<FindAssetKeyValueByIdAndKey>,
      ScaleWriter<FindAssetKeyValueByIdAndKey> {
    public override fun read(reader: ScaleCodecReader): FindAssetKeyValueByIdAndKey =
        FindAssetKeyValueByIdAndKey(jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.read(reader),
        jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: FindAssetKeyValueByIdAndKey):
        Unit {
      jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.write(writer, instance.id)
      jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.write(writer, instance.key)
    }
  }
}