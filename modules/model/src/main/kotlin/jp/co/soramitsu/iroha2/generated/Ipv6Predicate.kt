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

/**
 * Ipv6Predicate
 *
 * Generated from 'Ipv6Predicate' regular structure
 */
public data class Ipv6Predicate(
    public val arrayOfIntervalOfu16: Array<IntervalOfu16>
) {
    public companion object : ScaleReader<Ipv6Predicate>, ScaleWriter<Ipv6Predicate> {
        public override fun read(reader: ScaleCodecReader): Ipv6Predicate = try {
            Ipv6Predicate(
                reader.readArray(8) { IntervalOfu16.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Ipv6Predicate) = try {
            instance.arrayOfIntervalOfu16.forEach { value ->
                IntervalOfu16.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
