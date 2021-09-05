//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.schema.irohadatamodel.fixed

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.fixedPoint

/**
 * Fixed
 *
 * Generated from 'iroha_schema::iroha_data_model::fixed::Fixed' tuple structure
 */
public data class Fixed(
    public val fixedPoint: fixedPoint
) {
    public companion object : ScaleReader<Fixed>, ScaleWriter<Fixed> {
        public override fun read(reader: ScaleCodecReader): Fixed = Fixed(
            fixedPoint.read(reader),
        )

        public override fun write(writer: ScaleCodecWriter, instance: Fixed) {
            fixedPoint.write(writer, instance.fixedPoint)
        }
    }
}
