//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.time

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.core.time.Duration
import jp.co.soramitsu.iroha2.wrapException

/**
 * Interval
 *
 * Generated from 'iroha_data_model::events::time::Interval' regular structure
 */
public data class Interval(
    public val since: Duration,
    public val length: Duration
) {
    public companion object : ScaleReader<Interval>, ScaleWriter<Interval> {
        public override fun read(reader: ScaleCodecReader): Interval = try {
            Interval(
                Duration.read(reader),
                Duration.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Interval) = try {
            Duration.write(writer, instance.since)
            Duration.write(writer, instance.length)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
