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
 * EventSubscriptionRequest
 *
 * Generated from 'EventSubscriptionRequest' regular structure
 */
public data class EventSubscriptionRequest(
    public val filterBox: FilterBox
) {
    public companion object :
        ScaleReader<EventSubscriptionRequest>,
        ScaleWriter<EventSubscriptionRequest> {
        public override fun read(reader: ScaleCodecReader): EventSubscriptionRequest = try {
            EventSubscriptionRequest(
                FilterBox.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: EventSubscriptionRequest) = try {
            FilterBox.write(writer, instance.filterBox)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
