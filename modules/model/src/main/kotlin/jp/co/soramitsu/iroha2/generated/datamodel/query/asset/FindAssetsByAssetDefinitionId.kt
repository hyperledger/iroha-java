//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAssetsByAssetDefinitionId
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetsByAssetDefinitionId' regular structure
 */
public data class FindAssetsByAssetDefinitionId(
    public val assetDefinitionId: EvaluatesTo<DefinitionId>
) {
    public companion object :
        ScaleReader<FindAssetsByAssetDefinitionId>,
        ScaleWriter<FindAssetsByAssetDefinitionId> {
        public override fun read(reader: ScaleCodecReader): FindAssetsByAssetDefinitionId = try {
            FindAssetsByAssetDefinitionId(
                EvaluatesTo.read(reader) as EvaluatesTo<DefinitionId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAssetsByAssetDefinitionId) =
            try {
                EvaluatesTo.write(writer, instance.assetDefinitionId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
