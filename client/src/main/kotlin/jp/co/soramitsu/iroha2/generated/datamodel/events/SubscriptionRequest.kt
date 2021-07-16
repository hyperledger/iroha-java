//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Unit

/**
 * SubscriptionRequest
 *
 * Generated from 'iroha_data_model::events::SubscriptionRequest' tuple structure
 */
public class SubscriptionRequest(
  public val eventFilter: EventFilter
) {
  public companion object : ScaleReader<SubscriptionRequest>, ScaleWriter<SubscriptionRequest> {
    public override fun read(reader: ScaleCodecReader): SubscriptionRequest =
        SubscriptionRequest(EventFilter.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: SubscriptionRequest): Unit {
      EventFilter.write(writer, instance.eventFilter)
    }
  }
}
