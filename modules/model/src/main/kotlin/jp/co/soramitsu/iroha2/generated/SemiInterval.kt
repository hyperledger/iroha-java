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
 * SemiInterval
 *
 * Generated from 'SemiInterval<u32>' regular structure
 */
public data class SemiInterval<T0>(
    public val start: Long,
    public val limit: Long
) {
    public companion object : ScaleReader<SemiInterval<out Any>>, ScaleWriter<SemiInterval<out Any>> {
        public override fun read(reader: ScaleCodecReader): SemiInterval<out Any> = try {
            SemiInterval(
                reader.readUint32(),
                reader.readUint32(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SemiInterval<out Any>) = try {
            writer.writeUint32(instance.start)
            writer.writeUint32(instance.limit)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
