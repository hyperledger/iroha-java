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
 * AssetFilter
 *
 * Generated from 'AssetFilter' regular structure
 */
public data class AssetFilter(
    public val originFilter: FilterOptOfOriginFilterOfAssetEvent,
    public val eventFilter: FilterOptOfAssetEventFilter
) {
    public companion object : ScaleReader<AssetFilter>, ScaleWriter<AssetFilter> {
        public override fun read(reader: ScaleCodecReader): AssetFilter = try {
            AssetFilter(
                FilterOptOfOriginFilterOfAssetEvent.read(reader),
                FilterOptOfAssetEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetFilter) = try {
            FilterOptOfOriginFilterOfAssetEvent.write(writer, instance.originFilter)
            FilterOptOfAssetEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
