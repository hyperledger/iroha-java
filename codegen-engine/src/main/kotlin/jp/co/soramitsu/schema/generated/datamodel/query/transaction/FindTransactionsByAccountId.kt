// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.query.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo
import kotlin.Unit

/**
 * FindTransactionsByAccountId
 *
 * Generated from 'iroha_data_model::query::transaction::FindTransactionsByAccountId' regular
 * structure
 */
public class FindTransactionsByAccountId(
  private val accountId: EvaluatesTo
) : ScaleReader<FindTransactionsByAccountId>, ScaleWriter<FindTransactionsByAccountId> {
  public override fun read(reader: ScaleCodecReader): FindTransactionsByAccountId =
      FindTransactionsByAccountId(accountId.read(reader))

  public override fun write(writer: ScaleCodecWriter, instance: FindTransactionsByAccountId): Unit {
    accountId.write(writer, instance.accountId)
  }
}
