//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.fixed

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
 * Generated from 'iroha_data_model::fixed::Fixed' tuple structure
 */
public data class Fixed(
    public val fixedPoint: BigDecimal
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
            writer.writeUint64(instance.fixedPoint.toFixedPoint())
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
