//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.account

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.String
import kotlin.Unit

/**
 * FindAccountKeyValueByIdAndKey
 *
 * Generated from 'iroha_data_model::query::account::FindAccountKeyValueByIdAndKey' regular
 * structure
 */
public class FindAccountKeyValueByIdAndKey(
  public val id: EvaluatesTo<Id>,
  public val key: EvaluatesTo<String>
) {
  public companion object : ScaleReader<FindAccountKeyValueByIdAndKey>,
      ScaleWriter<FindAccountKeyValueByIdAndKey> {
    public override fun read(reader: ScaleCodecReader): FindAccountKeyValueByIdAndKey =
        FindAccountKeyValueByIdAndKey(
      EvaluatesTo<Id>.read(reader),
      EvaluatesTo<String>.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter, instance: FindAccountKeyValueByIdAndKey):
        Unit {
        EvaluatesTo<Id>.write(writer, instance.id)
        EvaluatesTo<String>.write(writer, instance.key)
    }
  }
}
