//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAssetQuantityById
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetQuantityById' regular structure
 */
public data class FindAssetQuantityById(
    public val id: EvaluatesTo<Id>
) {
    public companion object : ScaleReader<FindAssetQuantityById>, ScaleWriter<FindAssetQuantityById> {
        public override fun read(reader: ScaleCodecReader): FindAssetQuantityById = try {
            FindAssetQuantityById(
                EvaluatesTo.read(reader) as EvaluatesTo<Id>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAssetQuantityById) = try {
            EvaluatesTo.write(writer, instance.id)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
