//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.predicate.ipaddr

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.numerical.Interval
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Array
import kotlin.Int

/**
 * Ipv6Predicate
 *
 * Generated from 'iroha_data_model::predicate::ip_addr::Ipv6Predicate' tuple structure
 */
public data class Ipv6Predicate(
    public val array: Array<Interval<Int>>
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
            instance.array.forEach { value ->
                Interval.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
