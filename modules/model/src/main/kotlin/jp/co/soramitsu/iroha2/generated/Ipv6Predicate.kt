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
import kotlin.Int

/**
 * Ipv6Predicate
 *
 * Generated from 'Ipv6Predicate' regular structure
 */
public data class Ipv6Predicate(
    public val arrayOfU16: Array<Interval<Int>>
) {
    public companion object : ScaleReader<Ipv6Predicate>, ScaleWriter<Ipv6Predicate> {
        public override fun read(reader: ScaleCodecReader): Ipv6Predicate = try {
            Ipv6Predicate(
                reader.readArray(8) { Interval.read(reader) as Interval<Int> },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Ipv6Predicate) = try {
            instance.arrayOfU16.forEach { value ->
                Interval.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
