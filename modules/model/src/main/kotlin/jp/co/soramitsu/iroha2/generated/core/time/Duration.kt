//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.time

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigInteger
import kotlin.Long

/**
 * Duration
 *
 * Generated from 'core::time::Duration' tuple structure
 */
public data class Duration(
    public val u64: BigInteger,
    public val u32: Long
) {
    public companion object : ScaleReader<Duration>, ScaleWriter<Duration> {
        public override fun read(reader: ScaleCodecReader): Duration = try {
            Duration(
                reader.readUint64(),
                reader.readUint32(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Duration) = try {
            writer.writeUint64(instance.u64)
            writer.writeUint32(instance.u32)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
