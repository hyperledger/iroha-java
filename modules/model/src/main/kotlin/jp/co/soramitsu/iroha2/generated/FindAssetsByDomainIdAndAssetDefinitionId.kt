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
    public val domainId: DomainId,
    public val assetDefinitionId: AssetDefinitionId,
) {
    public companion object :
        ScaleReader<FindAssetsByDomainIdAndAssetDefinitionId>,
        ScaleWriter<FindAssetsByDomainIdAndAssetDefinitionId> {
        override fun read(reader: ScaleCodecReader): FindAssetsByDomainIdAndAssetDefinitionId = try {
            FindAssetsByDomainIdAndAssetDefinitionId(
                DomainId.read(reader),
                AssetDefinitionId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(
            writer: ScaleCodecWriter,
            instance: FindAssetsByDomainIdAndAssetDefinitionId,
        ): Unit = try {
            DomainId.write(writer, instance.domainId)
            AssetDefinitionId.write(writer, instance.assetDefinitionId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
