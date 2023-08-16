//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit

/**
 * FindAssetsByAssetDefinitionId
 *
 * Generated from 'FindAssetsByAssetDefinitionId' regular structure
 */
public data class FindAssetsByAssetDefinitionId(
    public val assetDefinitionId: EvaluatesTo<AssetDefinitionId>,
) {
    public companion object :
        ScaleReader<FindAssetsByAssetDefinitionId>,
        ScaleWriter<FindAssetsByAssetDefinitionId> {
        override fun read(reader: ScaleCodecReader): FindAssetsByAssetDefinitionId = try {
            FindAssetsByAssetDefinitionId(
                EvaluatesTo.read(reader) as EvaluatesTo<AssetDefinitionId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: FindAssetsByAssetDefinitionId): Unit =
            try {
                EvaluatesTo.write(writer, instance.assetDefinitionId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
