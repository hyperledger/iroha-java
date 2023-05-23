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
 * PeerFilter
 *
 * Generated from 'PeerFilter' regular structure
 */
public data class PeerFilter(
    public val originFilter: FilterOpt<OriginFilter<PeerEvent>>,
    public val eventFilter: FilterOpt<PeerEventFilter>
) {
    public companion object : ScaleReader<PeerFilter>, ScaleWriter<PeerFilter> {
        public override fun read(reader: ScaleCodecReader): PeerFilter = try {
            PeerFilter(
                FilterOpt.read(reader) as FilterOpt<OriginFilter<PeerEvent>>,
                FilterOpt.read(reader) as FilterOpt<PeerEventFilter>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PeerFilter) = try {
            FilterOpt.write(writer, instance.originFilter)
            FilterOpt.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
