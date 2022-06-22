//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.account

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAccountKeyValueByIdAndKey
 *
 * Generated from 'iroha_data_model::query::account::FindAccountKeyValueByIdAndKey' regular
 * structure
 */
public data class FindAccountKeyValueByIdAndKey(
    public val id: EvaluatesTo<AccountId>,
    public val key: EvaluatesTo<Name>
) {
    public companion object :
        ScaleReader<FindAccountKeyValueByIdAndKey>,
        ScaleWriter<FindAccountKeyValueByIdAndKey> {
        public override fun read(reader: ScaleCodecReader): FindAccountKeyValueByIdAndKey = try {
            FindAccountKeyValueByIdAndKey(
                EvaluatesTo.read(reader) as EvaluatesTo<AccountId>,
                EvaluatesTo.read(reader) as EvaluatesTo<Name>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAccountKeyValueByIdAndKey) =
            try {
                EvaluatesTo.write(writer, instance.id)
                EvaluatesTo.write(writer, instance.key)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
