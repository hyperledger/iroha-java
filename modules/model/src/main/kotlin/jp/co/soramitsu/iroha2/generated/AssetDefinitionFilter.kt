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
 * AssetDefinitionFilter
 *
 * Generated from 'AssetDefinitionFilter' regular structure
 */
public data class AssetDefinitionFilter(
    public val originFilter: FilterOpt<OriginFilter<AssetDefinitionEvent>>,
    public val eventFilter: FilterOpt<AssetDefinitionEventFilter>
) {
    public companion object : ScaleReader<AssetDefinitionFilter>, ScaleWriter<AssetDefinitionFilter> {
        public override fun read(reader: ScaleCodecReader): AssetDefinitionFilter = try {
            AssetDefinitionFilter(
                FilterOpt.read(reader) as FilterOpt<OriginFilter<AssetDefinitionEvent>>,
                FilterOpt.read(reader) as FilterOpt<AssetDefinitionEventFilter>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetDefinitionFilter) = try {
            FilterOpt.write(writer, instance.originFilter)
            FilterOpt.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
