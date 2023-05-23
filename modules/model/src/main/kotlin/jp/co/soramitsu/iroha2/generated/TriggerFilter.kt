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
 * TriggerFilter
 *
 * Generated from 'TriggerFilter' regular structure
 */
public data class TriggerFilter(
    public val originFilter: FilterOpt<OriginFilter<TriggerEvent>>,
    public val eventFilter: FilterOpt<TriggerEventFilter>
) {
    public companion object : ScaleReader<TriggerFilter>, ScaleWriter<TriggerFilter> {
        public override fun read(reader: ScaleCodecReader): TriggerFilter = try {
            TriggerFilter(
                FilterOpt.read(reader) as FilterOpt<OriginFilter<TriggerEvent>>,
                FilterOpt.read(reader) as FilterOpt<TriggerEventFilter>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: TriggerFilter) = try {
            FilterOpt.write(writer, instance.originFilter)
            FilterOpt.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
