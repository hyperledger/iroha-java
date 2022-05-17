//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.time

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.Duration
import jp.co.soramitsu.iroha2.wrapException

/**
 * Schedule
 *
 * Generated from 'iroha_data_model::events::time::Schedule' regular structure
 */
public data class Schedule(
    public val start: Duration,
    public val period: Duration?
) {
    public companion object : ScaleReader<Schedule>, ScaleWriter<Schedule> {
        public override fun read(reader: ScaleCodecReader): Schedule = try {
            Schedule(
                Duration.read(reader),
                reader.readNullable(Duration),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Schedule) = try {
            Duration.write(writer, instance.start)
            writer.writeNullable(Duration, instance.period)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
