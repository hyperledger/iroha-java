//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.account

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAccountById
 *
 * Generated from 'iroha_data_model::query::account::FindAccountById' regular structure
 */
public data class FindAccountById(
    public val id: EvaluatesTo<AccountId>
) {
    public companion object : ScaleReader<FindAccountById>, ScaleWriter<FindAccountById> {
        public override fun read(reader: ScaleCodecReader): FindAccountById = try {
            FindAccountById(
                EvaluatesTo.read(reader) as EvaluatesTo<AccountId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAccountById) = try {
            EvaluatesTo.write(writer, instance.id)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
