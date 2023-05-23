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
 * DomainFilter
 *
 * Generated from 'DomainFilter' regular structure
 */
public data class DomainFilter(
    public val originFilter: FilterOpt<OriginFilter<DomainEvent>>,
    public val eventFilter: FilterOpt<DomainEventFilter>
) {
    public companion object : ScaleReader<DomainFilter>, ScaleWriter<DomainFilter> {
        public override fun read(reader: ScaleCodecReader): DomainFilter = try {
            DomainFilter(
                FilterOpt.read(reader) as FilterOpt<OriginFilter<DomainEvent>>,
                FilterOpt.read(reader) as FilterOpt<DomainEventFilter>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: DomainFilter) = try {
            FilterOpt.write(writer, instance.originFilter)
            FilterOpt.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
