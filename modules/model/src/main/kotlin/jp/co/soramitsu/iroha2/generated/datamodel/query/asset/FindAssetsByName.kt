//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.String

/**
 * FindAssetsByName
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetsByName' regular structure
 */
public data class FindAssetsByName(
    public val name: EvaluatesTo<String>
) {
    public companion object : ScaleReader<FindAssetsByName>, ScaleWriter<FindAssetsByName> {
        public override fun read(reader: ScaleCodecReader): FindAssetsByName = FindAssetsByName(
            EvaluatesTo.read(reader) as EvaluatesTo<String>,
        )

        public override fun write(writer: ScaleCodecWriter, instance: FindAssetsByName) {
            EvaluatesTo.write(writer, instance.name)
        }
    }
}
