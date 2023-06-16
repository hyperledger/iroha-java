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
 * TimeEvent
 *
 * Generated from 'iroha_data_model::events::time::TimeEvent' regular structure
 */
public data class TimeEvent(
    public val prevInterval: Interval? = null,
    public val interval: Interval
) {
    public companion object : ScaleReader<TimeEvent>, ScaleWriter<TimeEvent> {
        public override fun read(reader: ScaleCodecReader): TimeEvent = try {
            TimeEvent(
                reader.readNullable(Interval) as Interval?,
                Interval.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: TimeEvent) = try {
            writer.writeNullable(Interval, instance.prevInterval)
            Interval.write(writer, instance.interval)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
