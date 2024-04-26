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
 * AssetDefinitionFilter
 *
 * Generated from 'AssetDefinitionFilter' regular structure
 */
public data class AssetDefinitionFilter(
    public val originFilter: FilterOptOfOriginFilterOfAssetDefinitionEvent,
    public val eventFilter: FilterOptOfAssetDefinitionEventFilter,
) {
    public companion object : ScaleReader<AssetDefinitionFilter>, ScaleWriter<AssetDefinitionFilter> {
        override fun read(reader: ScaleCodecReader): AssetDefinitionFilter = try {
            AssetDefinitionFilter(
                FilterOptOfOriginFilterOfAssetDefinitionEvent.read(reader),
                FilterOptOfAssetDefinitionEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: AssetDefinitionFilter): Unit = try {
            FilterOptOfOriginFilterOfAssetDefinitionEvent.write(writer, instance.originFilter)
            FilterOptOfAssetDefinitionEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
