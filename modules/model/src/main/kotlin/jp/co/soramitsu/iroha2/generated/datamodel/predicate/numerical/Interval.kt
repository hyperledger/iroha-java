//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.predicate.numerical

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.Short

/**
 * Interval
 *
 * Generated from 'iroha_data_model::predicate::numerical::Interval<u8>' regular structure
 */
public data class Interval<T0>(
    public val start: Short,
    public val limit: Short
) {
    public companion object : ScaleReader<Interval<out Any>>, ScaleWriter<Interval<out Any>> {
        public override fun read(reader: ScaleCodecReader): Interval<out Any> = try {
            Interval(
                reader.readUByte().toShort(),
                reader.readUByte().toShort(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Interval<out Any>) = try {
            writer.writeUByte(instance.start)
            writer.writeUByte(instance.limit)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
