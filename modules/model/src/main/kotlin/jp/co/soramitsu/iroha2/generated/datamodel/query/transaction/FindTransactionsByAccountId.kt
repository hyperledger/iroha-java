//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindTransactionsByAccountId
 *
 * Generated from 'iroha_data_model::query::transaction::FindTransactionsByAccountId' regular
 * structure
 */
public data class FindTransactionsByAccountId(
    public val accountId: EvaluatesTo<Id>
) {
    public companion object :
        ScaleReader<FindTransactionsByAccountId>,
        ScaleWriter<FindTransactionsByAccountId> {
        public override fun read(reader: ScaleCodecReader): FindTransactionsByAccountId = try {
            FindTransactionsByAccountId(
                EvaluatesTo.read(reader) as EvaluatesTo<Id>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindTransactionsByAccountId) = try {
            EvaluatesTo.write(writer, instance.accountId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
