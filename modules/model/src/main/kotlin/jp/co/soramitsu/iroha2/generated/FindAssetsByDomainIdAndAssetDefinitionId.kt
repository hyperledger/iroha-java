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
 * FindAssetsByDomainIdAndAssetDefinitionId
 *
 * Generated from 'FindAssetsByDomainIdAndAssetDefinitionId' regular structure
 */
public data class FindAssetsByDomainIdAndAssetDefinitionId(
    public val domainId: EvaluatesTo<DomainId>,
    public val assetDefinitionId: EvaluatesTo<AssetDefinitionId>,
) {
    public companion object :
        ScaleReader<FindAssetsByDomainIdAndAssetDefinitionId>,
        ScaleWriter<FindAssetsByDomainIdAndAssetDefinitionId> {
        override fun read(reader: ScaleCodecReader): FindAssetsByDomainIdAndAssetDefinitionId = try {
            FindAssetsByDomainIdAndAssetDefinitionId(
                EvaluatesTo.read(reader) as EvaluatesTo<DomainId>,
                EvaluatesTo.read(reader) as EvaluatesTo<AssetDefinitionId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(
            writer: ScaleCodecWriter,
            instance: FindAssetsByDomainIdAndAssetDefinitionId,
        ): Unit = try {
            EvaluatesTo.write(writer, instance.domainId)
            EvaluatesTo.write(writer, instance.assetDefinitionId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
