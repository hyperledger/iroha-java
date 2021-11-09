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
 * SubscriptionRequest
 *
 * Generated from 'iroha_data_model::events::SubscriptionRequest' tuple structure
 */
public data class SubscriptionRequest(
    public val eventFilter: EventFilter
) {
    public companion object : ScaleReader<SubscriptionRequest>, ScaleWriter<SubscriptionRequest> {
        public override fun read(reader: ScaleCodecReader): SubscriptionRequest = try {
            SubscriptionRequest(
                EventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SubscriptionRequest) = try {
            EventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
