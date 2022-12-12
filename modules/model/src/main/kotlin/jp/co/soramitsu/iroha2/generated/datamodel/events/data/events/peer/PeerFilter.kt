//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.peer

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptOriginFilterPeerEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptPeerEventFilter
import jp.co.soramitsu.iroha2.wrapException

/**
 * PeerFilter
 *
 * Generated from 'iroha_data_model::events::data::events::peer::PeerFilter' regular structure
 */
public data class PeerFilter(
    public val originFilter: FilterOptOriginFilterPeerEvent,
    public val eventFilter: FilterOptPeerEventFilter
) {
    public companion object : ScaleReader<PeerFilter>, ScaleWriter<PeerFilter> {
        public override fun read(reader: ScaleCodecReader): PeerFilter = try {
            PeerFilter(
                FilterOptOriginFilterPeerEvent.read(reader),
                FilterOptPeerEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PeerFilter) = try {
            FilterOptOriginFilterPeerEvent.write(writer, instance.originFilter)
            FilterOptPeerEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
