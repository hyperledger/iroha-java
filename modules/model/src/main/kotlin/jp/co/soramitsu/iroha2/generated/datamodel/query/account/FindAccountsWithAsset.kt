//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.account

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAccountsWithAsset
 *
 * Generated from 'iroha_data_model::query::account::FindAccountsWithAsset' regular structure
 */
public data class FindAccountsWithAsset(
    public val assetDefinitionId: EvaluatesTo<DefinitionId>
) {
    public companion object : ScaleReader<FindAccountsWithAsset>, ScaleWriter<FindAccountsWithAsset> {
        public override fun read(reader: ScaleCodecReader): FindAccountsWithAsset = try {
            FindAccountsWithAsset(
                EvaluatesTo.read(reader) as EvaluatesTo<DefinitionId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAccountsWithAsset) = try {
            EvaluatesTo.write(writer, instance.assetDefinitionId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
