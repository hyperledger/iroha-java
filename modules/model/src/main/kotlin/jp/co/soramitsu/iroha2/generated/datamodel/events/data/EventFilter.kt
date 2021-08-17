//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter

/**
 * EventFilter
 *
 * Generated from 'iroha_data_model::events::data::EventFilter' tuple structure
 */
public class EventFilter {
    public companion object : ScaleReader<EventFilter>, ScaleWriter<EventFilter> {
        public override fun read(reader: ScaleCodecReader): EventFilter = EventFilter()

        public override fun write(writer: ScaleCodecWriter, instance: EventFilter) {
        }
    }
}
