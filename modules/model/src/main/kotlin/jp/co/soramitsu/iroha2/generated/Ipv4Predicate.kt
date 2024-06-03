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
import kotlin.Unit

/**
 * Ipv4Predicate
 *
 * Generated from 'Ipv4Predicate' regular structure
 */
public data class Ipv4Predicate(
    public val arrayOfIntervalOfu8: Array<IntervalOfu8>,
) {
    public companion object : ScaleReader<Ipv4Predicate>, ScaleWriter<Ipv4Predicate> {
        override fun read(reader: ScaleCodecReader): Ipv4Predicate = try {
            Ipv4Predicate(
                reader.readArray(4) { IntervalOfu8.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: Ipv4Predicate): Unit = try {
            instance.arrayOfIntervalOfu8.forEach { value ->
                IntervalOfu8.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
