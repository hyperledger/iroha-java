//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAssetsByAccountId
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetsByAccountId' regular structure
 */
public data class FindAssetsByAccountId(
    public val accountId: EvaluatesTo<AccountId>
) {
    public companion object : ScaleReader<FindAssetsByAccountId>, ScaleWriter<FindAssetsByAccountId> {
        public override fun read(reader: ScaleCodecReader): FindAssetsByAccountId = try {
            FindAssetsByAccountId(
                EvaluatesTo.read(reader) as EvaluatesTo<AccountId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAssetsByAccountId) = try {
            EvaluatesTo.write(writer, instance.accountId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
