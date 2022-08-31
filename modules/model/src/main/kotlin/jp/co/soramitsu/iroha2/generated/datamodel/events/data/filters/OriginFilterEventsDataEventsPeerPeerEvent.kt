//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.peer.PeerId
import jp.co.soramitsu.iroha2.wrapException

/**
 * OriginFilterEventsDataEventsPeerPeerEvent
 *
 * Generated from
 * 'iroha_data_model::events::data::filters::OriginFilterEventsDataEventsPeerPeerEvent' tuple structure
 */
public data class OriginFilterEventsDataEventsPeerPeerEvent(
    public val peerId: PeerId
) {
    public companion object :
        ScaleReader<OriginFilterEventsDataEventsPeerPeerEvent>,
        ScaleWriter<OriginFilterEventsDataEventsPeerPeerEvent> {
        public override fun read(reader: ScaleCodecReader): OriginFilterEventsDataEventsPeerPeerEvent =
            try {
                OriginFilterEventsDataEventsPeerPeerEvent(
                    PeerId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

        public override fun write(
            writer: ScaleCodecWriter,
            instance: OriginFilterEventsDataEventsPeerPeerEvent
        ) = try {
            PeerId.write(writer, instance.peerId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
