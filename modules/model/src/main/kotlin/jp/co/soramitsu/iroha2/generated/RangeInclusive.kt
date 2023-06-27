//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.Long

/**
 * RangeInclusive
 *
 * Generated from 'RangeInclusive<u32>' tuple structure
 */
public data class RangeInclusive<T0>(
    public val p1U32: Long,
    public val p2U32: Long
) {
    public companion object :
        ScaleReader<RangeInclusive<out Any>>,
        ScaleWriter<RangeInclusive<out
                Any>> {
        public override fun read(reader: ScaleCodecReader): RangeInclusive<out Any> = try {
            RangeInclusive(
                reader.readUint32(),
                reader.readUint32(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: RangeInclusive<out Any>) = try {
            writer.writeUint32(instance.p1U32)
            writer.writeUint32(instance.p2U32)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
