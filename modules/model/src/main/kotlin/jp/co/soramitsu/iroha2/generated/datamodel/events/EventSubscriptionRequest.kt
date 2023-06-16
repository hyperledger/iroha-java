//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * EventSubscriptionRequest
 *
 * Generated from 'iroha_data_model::events::EventSubscriptionRequest' tuple structure
 */
public data class EventSubscriptionRequest(
    public val eventsFilterBox: EventsFilterBox
) {
    public companion object :
        ScaleReader<EventSubscriptionRequest>,
        ScaleWriter<EventSubscriptionRequest> {
        public override fun read(reader: ScaleCodecReader): EventSubscriptionRequest = try {
            EventSubscriptionRequest(
                EventsFilterBox.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: EventSubscriptionRequest) = try {
            EventsFilterBox.write(writer, instance.eventsFilterBox)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
