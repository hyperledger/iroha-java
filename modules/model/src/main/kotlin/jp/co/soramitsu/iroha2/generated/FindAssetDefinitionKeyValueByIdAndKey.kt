//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAssetDefinitionKeyValueByIdAndKey
 *
 * Generated from 'FindAssetDefinitionKeyValueByIdAndKey' regular structure
 */
public data class FindAssetDefinitionKeyValueByIdAndKey(
    public val id: EvaluatesTo<AssetDefinitionId>,
    public val key: EvaluatesTo<Name>
) {
    public companion object :
        ScaleReader<FindAssetDefinitionKeyValueByIdAndKey>,
        ScaleWriter<FindAssetDefinitionKeyValueByIdAndKey> {
        public override fun read(reader: ScaleCodecReader): FindAssetDefinitionKeyValueByIdAndKey = try {
            FindAssetDefinitionKeyValueByIdAndKey(
                EvaluatesTo.read(reader) as EvaluatesTo<AssetDefinitionId>,
                EvaluatesTo.read(reader) as EvaluatesTo<Name>,
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
