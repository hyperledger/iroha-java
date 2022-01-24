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
 * EventFilter
 *
 * Generated from 'iroha_data_model::events::data::EventFilter' regular structure
 */
public data class EventFilter(
    public val entity: EntityFilter?,
    public val status: StatusFilter?
) {
    public companion object : ScaleReader<EventFilter>, ScaleWriter<EventFilter> {
        public override fun read(reader: ScaleCodecReader): EventFilter = try {
            EventFilter(
                reader.readNullable(EntityFilter),
                reader.readNullable(StatusFilter),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: EventFilter) = try {
            writer.writeNullable(EntityFilter, instance.entity)
            writer.writeNullable(StatusFilter, instance.status)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
