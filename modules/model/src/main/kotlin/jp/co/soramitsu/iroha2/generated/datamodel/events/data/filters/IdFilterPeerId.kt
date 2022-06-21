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
 * IdFilterPeerId
 *
 * Generated from 'iroha_data_model::events::data::filters::IdFilterPeerId' tuple structure
 */
public data class IdFilterPeerId(
    public val peerId: PeerId
) {
    public companion object : ScaleReader<IdFilterPeerId>, ScaleWriter<IdFilterPeerId> {
        public override fun read(reader: ScaleCodecReader): IdFilterPeerId = try {
            IdFilterPeerId(
                PeerId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: IdFilterPeerId) = try {
            PeerId.write(writer, instance.peerId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
