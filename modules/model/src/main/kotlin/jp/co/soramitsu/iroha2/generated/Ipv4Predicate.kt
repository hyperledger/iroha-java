//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Array
import kotlin.Short

/**
 * Ipv4Predicate
 *
 * Generated from 'Ipv4Predicate' regular structure
 */
public data class Ipv4Predicate(
    public val arrayOfU8: Array<Interval<Short>>
) {
    public companion object : ScaleReader<Ipv4Predicate>, ScaleWriter<Ipv4Predicate> {
        public override fun read(reader: ScaleCodecReader): Ipv4Predicate = try {
            Ipv4Predicate(
                reader.readArray(4) { Interval.read(reader) as Interval<Short> },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Ipv4Predicate) = try {
            instance.arrayOfU8.forEach { value ->
                Interval.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
