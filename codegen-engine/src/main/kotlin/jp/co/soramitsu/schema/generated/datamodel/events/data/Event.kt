// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.events.`data`

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleReader

/**
 * Event
 *
 * Generated from 'iroha_data_model::events::data::Event' tuple structure
 */
public class Event {
  public companion object READER : ScaleReader<Event> {
    public override fun read(reader: ScaleCodecReader): Event = Event()
  }
}
