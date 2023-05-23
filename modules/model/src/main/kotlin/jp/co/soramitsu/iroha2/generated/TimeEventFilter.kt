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
 * TimeEventFilter
 *
 * Generated from 'TimeEventFilter' regular structure
 */
public data class TimeEventFilter(
    public val executionTime: ExecutionTime
) {
    public companion object : ScaleReader<TimeEventFilter>, ScaleWriter<TimeEventFilter> {
        public override fun read(reader: ScaleCodecReader): TimeEventFilter = try {
            TimeEventFilter(
                ExecutionTime.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: TimeEventFilter) = try {
            ExecutionTime.write(writer, instance.executionTime)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
