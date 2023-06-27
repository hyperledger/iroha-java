//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Short

/**
 * IntervalOfu8
 *
 * Generated from 'IntervalOfu8' regular structure
 */
public data class IntervalOfu8(
    public val start: Short,
    public val limit: Short
) {
    public companion object : ScaleReader<IntervalOfu8>, ScaleWriter<IntervalOfu8> {
        public override fun read(reader: ScaleCodecReader): IntervalOfu8 = try {
            IntervalOfu8(
                reader.readUByte().toShort(),
                reader.readUByte().toShort(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: IntervalOfu8) = try {
            writer.writeUByte(instance.start)
            writer.writeUByte(instance.limit)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
