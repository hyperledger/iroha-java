//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Boolean

/**
 * Or
 *
 * Generated from 'iroha_data_model::expression::Or' regular structure
 */
public data class Or(
    public val left: EvaluatesTo<Boolean>,
    public val right: EvaluatesTo<Boolean>
) {
    public companion object : ScaleReader<Or>, ScaleWriter<Or> {
        public override fun read(reader: ScaleCodecReader): Or = Or(
            EvaluatesTo.read(reader) as EvaluatesTo<Boolean>,
            EvaluatesTo.read(reader) as EvaluatesTo<Boolean>,
        )

        public override fun write(writer: ScaleCodecWriter, instance: Or) {
            EvaluatesTo.write(writer, instance.left)
            EvaluatesTo.write(writer, instance.right)
        }
    }
}
