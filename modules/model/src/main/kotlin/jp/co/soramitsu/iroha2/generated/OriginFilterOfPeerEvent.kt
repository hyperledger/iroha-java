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
 * OriginFilterOfPeerEvent
 *
 * Generated from 'OriginFilterOfPeerEvent' regular structure
 */
public data class OriginFilterOfPeerEvent(
    public val peerId: PeerId
) {
    public companion object :
        ScaleReader<OriginFilterOfPeerEvent>,
        ScaleWriter<OriginFilterOfPeerEvent> {
        public override fun read(reader: ScaleCodecReader): OriginFilterOfPeerEvent = try {
            OriginFilterOfPeerEvent(
                PeerId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: OriginFilterOfPeerEvent) = try {
            PeerId.write(writer, instance.peerId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
