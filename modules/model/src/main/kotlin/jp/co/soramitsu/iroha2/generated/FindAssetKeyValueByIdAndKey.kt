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
 * FindAssetKeyValueByIdAndKey
 *
 * Generated from 'FindAssetKeyValueByIdAndKey' regular structure
 */
public data class FindAssetKeyValueByIdAndKey(
    public val id: EvaluatesTo<AssetId>,
    public val key: EvaluatesTo<Name>
) {
    public companion object :
        ScaleReader<FindAssetKeyValueByIdAndKey>,
        ScaleWriter<FindAssetKeyValueByIdAndKey> {
        public override fun read(reader: ScaleCodecReader): FindAssetKeyValueByIdAndKey = try {
            FindAssetKeyValueByIdAndKey(
                EvaluatesTo.read(reader) as EvaluatesTo<AssetId>,
                EvaluatesTo.read(reader) as EvaluatesTo<Name>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAssetKeyValueByIdAndKey) = try {
            EvaluatesTo.write(writer, instance.id)
            EvaluatesTo.write(writer, instance.key)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
