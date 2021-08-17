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
 * Greater
 *
 * Generated from 'iroha_data_model::expression::Greater' regular structure
 */
public data class Greater(
    public val left: EvaluatesTo<UInt>,
    public val right: EvaluatesTo<UInt>
) {
    public companion object : ScaleReader<Greater>, ScaleWriter<Greater> {
        public override fun read(reader: ScaleCodecReader): Greater = Greater(
            EvaluatesTo.read(reader) as EvaluatesTo<UInt>,
            EvaluatesTo.read(reader) as EvaluatesTo<UInt>,
        )

        public override fun write(writer: ScaleCodecWriter, instance: Greater) {
            EvaluatesTo.write(writer, instance.left)
            EvaluatesTo.write(writer, instance.right)
        }
    }
}
