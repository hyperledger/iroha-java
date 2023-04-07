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
 * FindAssetDefinitionById
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetDefinitionById' regular structure
 */
public data class FindAssetDefinitionById(
    public val id: EvaluatesTo<DefinitionId>
) {
    public companion object :
        ScaleReader<FindAssetDefinitionById>,
        ScaleWriter<FindAssetDefinitionById> {
        public override fun read(reader: ScaleCodecReader): FindAssetDefinitionById = try {
            FindAssetDefinitionById(
                EvaluatesTo.read(reader) as EvaluatesTo<DefinitionId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAssetDefinitionById) = try {
            EvaluatesTo.write(writer, instance.id)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
