//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo

/**
 * FindAssetsByAccountIdAndAssetDefinitionId
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetsByAccountIdAndAssetDefinitionId'
 * regular structure
 */
public class FindAssetsByAccountIdAndAssetDefinitionId(
    public val accountId: EvaluatesTo<Id>,
    public val assetDefinitionId: EvaluatesTo<DefinitionId>
) {
    public companion object :
        ScaleReader<FindAssetsByAccountIdAndAssetDefinitionId>,
        ScaleWriter<FindAssetsByAccountIdAndAssetDefinitionId> {
        public override fun read(reader: ScaleCodecReader): FindAssetsByAccountIdAndAssetDefinitionId =
            FindAssetsByAccountIdAndAssetDefinitionId(
                EvaluatesTo.read(reader) as EvaluatesTo<Id>,
                EvaluatesTo.read(reader) as EvaluatesTo<DefinitionId>,
            )

        public override fun write(
            writer: ScaleCodecWriter,
            instance: FindAssetsByAccountIdAndAssetDefinitionId
        ) {
            EvaluatesTo.write(writer, instance.accountId)
            EvaluatesTo.write(writer, instance.assetDefinitionId)
        }
    }
}
