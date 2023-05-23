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
    public val originFilter: FilterOpt<OriginFilter<AssetEvent>>,
    public val eventFilter: FilterOpt<AssetEventFilter>
) {
    public companion object : ScaleReader<AssetFilter>, ScaleWriter<AssetFilter> {
        public override fun read(reader: ScaleCodecReader): AssetFilter = try {
            AssetFilter(
                FilterOpt.read(reader) as FilterOpt<OriginFilter<AssetEvent>>,
                FilterOpt.read(reader) as FilterOpt<AssetEventFilter>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetFilter) = try {
            FilterOpt.write(writer, instance.originFilter)
            FilterOpt.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
