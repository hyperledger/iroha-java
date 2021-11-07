//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.fixed

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.fromFixedPoint
import jp.co.soramitsu.iroha2.readBit64
import jp.co.soramitsu.iroha2.toFixedPoint
import jp.co.soramitsu.iroha2.wrapException
import jp.co.soramitsu.iroha2.writeBit64
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
                readBit64(reader).fromFixedPoint(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Fixed) = try {
            writeBit64(writer, instance.fixedPoint.toFixedPoint())
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
