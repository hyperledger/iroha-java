//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.permissions

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindPermissionTokensByAccountId
 *
 * Generated from 'iroha_data_model::query::permissions::FindPermissionTokensByAccountId' regular
 * structure
 */
public data class FindPermissionTokensByAccountId(
    public val id: EvaluatesTo<AccountId>
) {
    public companion object :
        ScaleReader<FindPermissionTokensByAccountId>,
        ScaleWriter<FindPermissionTokensByAccountId> {
        public override fun read(reader: ScaleCodecReader): FindPermissionTokensByAccountId = try {
            FindPermissionTokensByAccountId(
                EvaluatesTo.read(reader) as EvaluatesTo<AccountId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindPermissionTokensByAccountId) =
            try {
                EvaluatesTo.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
