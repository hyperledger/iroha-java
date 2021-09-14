//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.fixed

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.readBit64
import jp.co.soramitsu.iroha2.wrapException
import jp.co.soramitsu.iroha2.writeBit64
import kotlin.Long

/**
 * Fixed
 *
 * Generated from 'iroha_data_model::fixed::Fixed' tuple structure
 */
public data class Fixed(
    public val fixedPoint: Long
) {
    public companion object : ScaleReader<Fixed>, ScaleWriter<Fixed> {
        public override fun read(reader: ScaleCodecReader): Fixed = try {
            Fixed(
                readBit64(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Fixed) = try {
            writeBit64(writer, instance.fixedPoint)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
