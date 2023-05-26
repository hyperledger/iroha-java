//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * IntervalOfu16
 *
 * Generated from 'IntervalOfu16' regular structure
 */
public data class IntervalOfu16(
    public val start: Int,
    public val limit: Int
) {
    public companion object : ScaleReader<IntervalOfu16>, ScaleWriter<IntervalOfu16> {
        public override fun read(reader: ScaleCodecReader): IntervalOfu16 = try {
            IntervalOfu16(
                reader.readUint16(),
                reader.readUint16(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: IntervalOfu16) = try {
            writer.writeUint16(instance.start)
            writer.writeUint16(instance.limit)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
