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
import jp.co.soramitsu.iroha2.wrapException
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
        public override fun read(reader: ScaleCodecReader): FindAssetDefinitionKeyValueByIdAndKey = try {
            FindAssetDefinitionKeyValueByIdAndKey(
                EvaluatesTo.read(reader) as EvaluatesTo<DefinitionId>,
                EvaluatesTo.read(reader) as EvaluatesTo<String>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(
            writer: ScaleCodecWriter,
            instance: FindAssetDefinitionKeyValueByIdAndKey
        ) = try {
            EvaluatesTo.write(writer, instance.id)
            EvaluatesTo.write(writer, instance.key)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
