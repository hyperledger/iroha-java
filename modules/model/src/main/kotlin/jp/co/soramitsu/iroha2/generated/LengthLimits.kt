//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Long

/**
 * LengthLimits
 *
 * Generated from 'LengthLimits' regular structure
 */
public data class LengthLimits(
    public val min: Long,
    public val max: Long
) {
    public companion object : ScaleReader<LengthLimits>, ScaleWriter<LengthLimits> {
        public override fun read(reader: ScaleCodecReader): LengthLimits = try {
            LengthLimits(
                reader.readUint32(),
                reader.readUint32(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: LengthLimits) = try {
            writer.writeUint32(instance.min)
            writer.writeUint32(instance.max)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
