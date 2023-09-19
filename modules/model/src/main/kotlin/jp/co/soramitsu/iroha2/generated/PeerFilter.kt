//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit

/**
 * PeerFilter
 *
 * Generated from 'PeerFilter' regular structure
 */
public data class PeerFilter(
    public val originFilter: FilterOptOfOriginFilterOfPeerEvent,
    public val eventFilter: FilterOptOfPeerEventFilter,
) {
    public companion object : ScaleReader<PeerFilter>, ScaleWriter<PeerFilter> {
        override fun read(reader: ScaleCodecReader): PeerFilter = try {
            PeerFilter(
                FilterOptOfOriginFilterOfPeerEvent.read(reader),
                FilterOptOfPeerEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: PeerFilter): Unit = try {
            FilterOptOfOriginFilterOfPeerEvent.write(writer, instance.originFilter)
            FilterOptOfPeerEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
