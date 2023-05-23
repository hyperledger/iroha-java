//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.fromFixedPoint
import jp.co.soramitsu.iroha2.toFixedPoint
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigDecimal

/**
 * Fixed
 *
 * Generated from 'Fixed' regular structure
 */
public data class Fixed(
    public val fixedPointOfI64: BigDecimal
) {
    public companion object : ScaleReader<Fixed>, ScaleWriter<Fixed> {
        public override fun read(reader: ScaleCodecReader): Fixed = try {
            Fixed(
                reader.readInt64().toBigInteger().fromFixedPoint(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Fixed) = try {
            writer.writeInt64(instance.fixedPointOfI64.toFixedPoint().toLong())
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
