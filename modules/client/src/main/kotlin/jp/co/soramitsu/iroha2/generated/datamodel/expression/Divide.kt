//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.UInt

/**
 * Divide
 *
 * Generated from 'iroha_data_model::expression::Divide' regular structure
 */
public class Divide(
    public val left: EvaluatesTo<UInt>,
    public val right: EvaluatesTo<UInt>
) {
    public companion object : ScaleReader<Divide>, ScaleWriter<Divide> {
        public override fun read(reader: ScaleCodecReader): Divide = Divide(
            EvaluatesTo.read(reader) as EvaluatesTo<UInt>,
            EvaluatesTo.read(reader) as EvaluatesTo<UInt>,
        )

        public override fun write(writer: ScaleCodecWriter, instance: Divide) {
            EvaluatesTo.write(writer, instance.left)
            EvaluatesTo.write(writer, instance.right)
        }
    }
}
