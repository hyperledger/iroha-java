//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.time

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * EventFilter
 *
 * Generated from 'iroha_data_model::events::time::EventFilter' tuple structure
 */
public data class EventFilter(
    public val executionTime: ExecutionTime
) {
    public companion object : ScaleReader<EventFilter>, ScaleWriter<EventFilter> {
        public override fun read(reader: ScaleCodecReader): EventFilter = try {
            EventFilter(
                ExecutionTime.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: EventFilter) = try {
            ExecutionTime.write(writer, instance.executionTime)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
