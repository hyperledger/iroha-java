//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.ByteArray

/**
 * Ipv4Addr
 *
 * Generated from 'Ipv4Addr' regular structure
 */
public data class Ipv4Addr(
    public val arrayOfU8: ByteArray
) {
    public companion object : ScaleReader<Ipv4Addr>, ScaleWriter<Ipv4Addr> {
        public override fun read(reader: ScaleCodecReader): Ipv4Addr = try {
            Ipv4Addr(
                reader.readByteArray(4),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Ipv4Addr) = try {
            writer.writeByteArray(instance.arrayOfU8)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
