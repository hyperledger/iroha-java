//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigInteger
import kotlin.Unit

/**
 * SemiIntervalOfu128
 *
 * Generated from 'SemiIntervalOfu128' regular structure
 */
public data class SemiIntervalOfu128(
    public val start: BigInteger,
    public val limit: BigInteger,
) {
    public companion object : ScaleReader<SemiIntervalOfu128>, ScaleWriter<SemiIntervalOfu128> {
        override fun read(reader: ScaleCodecReader): SemiIntervalOfu128 = try {
            SemiIntervalOfu128(
                reader.readUint128(),
                reader.readUint128(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: SemiIntervalOfu128): Unit = try {
            writer.writeUint128(instance.start)
            writer.writeUint128(instance.limit)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
