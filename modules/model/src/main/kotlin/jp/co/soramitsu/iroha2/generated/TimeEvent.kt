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
 * TimeEvent
 *
 * Generated from 'TimeEvent' regular structure
 */
public data class TimeEvent(
    public val prevInterval: TimeInterval? = null,
    public val interval: TimeInterval
) {
    public companion object : ScaleReader<TimeEvent>, ScaleWriter<TimeEvent> {
        public override fun read(reader: ScaleCodecReader): TimeEvent = try {
            TimeEvent(
                reader.readNullable(TimeInterval) as TimeInterval?,
                TimeInterval.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: TimeEvent) = try {
            writer.writeNullable(TimeInterval, instance.prevInterval)
            TimeInterval.write(writer, instance.interval)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
