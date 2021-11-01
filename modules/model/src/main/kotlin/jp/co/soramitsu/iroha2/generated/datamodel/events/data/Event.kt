//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * Event
 *
 * Generated from 'iroha_data_model::events::data::Event' tuple structure
 */
public class Event {
    public companion object : ScaleReader<Event>, ScaleWriter<Event> {
        public override fun read(reader: ScaleCodecReader): Event = try {
            Event()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Event) = try {
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
