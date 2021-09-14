//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.String

/**
 * FindAssetDefinitionKeyValueByIdAndKey
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetDefinitionKeyValueByIdAndKey' regular
 * structure
 */
public data class FindAssetDefinitionKeyValueByIdAndKey(
    public val id: EvaluatesTo<DefinitionId>,
    public val key: EvaluatesTo<String>
) {
    public companion object :
        ScaleReader<FindAssetDefinitionKeyValueByIdAndKey>,
        ScaleWriter<FindAssetDefinitionKeyValueByIdAndKey> {
        public override fun read(reader: ScaleCodecReader): FindAssetDefinitionKeyValueByIdAndKey =
            FindAssetDefinitionKeyValueByIdAndKey(
                EvaluatesTo.read(reader) as EvaluatesTo<DefinitionId>,
                EvaluatesTo.read(reader) as EvaluatesTo<String>,
            )

        public override fun write(
            writer: ScaleCodecWriter,
            instance: FindAssetDefinitionKeyValueByIdAndKey
        ) {
            EvaluatesTo.write(writer, instance.id)
            EvaluatesTo.write(writer, instance.key)
        }
    }
}
