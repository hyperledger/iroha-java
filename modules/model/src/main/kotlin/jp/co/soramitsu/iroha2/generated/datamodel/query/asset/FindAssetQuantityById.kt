//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo

/**
 * FindAssetQuantityById
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetQuantityById' regular structure
 */
public data class FindAssetQuantityById(
    public val id: EvaluatesTo<Id>
) {
    public companion object : ScaleReader<FindAssetQuantityById>, ScaleWriter<FindAssetQuantityById> {
        public override fun read(reader: ScaleCodecReader): FindAssetQuantityById =
            FindAssetQuantityById(
                EvaluatesTo.read(reader) as EvaluatesTo<Id>,
            )

        public override fun write(writer: ScaleCodecWriter, instance: FindAssetQuantityById) {
            EvaluatesTo.write(writer, instance.id)
        }
    }
}
