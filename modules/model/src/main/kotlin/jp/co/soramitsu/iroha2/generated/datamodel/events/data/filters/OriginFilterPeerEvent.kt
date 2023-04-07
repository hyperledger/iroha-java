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
 * OriginFilterPeerEvent
 *
 * Generated from 'iroha_data_model::events::data::filters::OriginFilterPeerEvent' tuple structure
 */
public data class OriginFilterPeerEvent(
    public val peerId: PeerId
) {
    public companion object : ScaleReader<OriginFilterPeerEvent>, ScaleWriter<OriginFilterPeerEvent> {
        public override fun read(reader: ScaleCodecReader): OriginFilterPeerEvent = try {
            OriginFilterPeerEvent(
                PeerId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: OriginFilterPeerEvent) = try {
            PeerId.write(writer, instance.peerId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
