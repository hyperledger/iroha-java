//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo

/**
 * SetBox
 *
 * Generated from 'iroha_data_model::isi::SetBox' regular structure
 */
public data class SetBox(
    public val `object`: EvaluatesTo<Value>
) {
    public companion object : ScaleReader<SetBox>, ScaleWriter<SetBox> {
        public override fun read(reader: ScaleCodecReader): SetBox = SetBox(
            EvaluatesTo.read(reader) as EvaluatesTo<Value>,
        )

        public override fun write(writer: ScaleCodecWriter, instance: SetBox) {
            EvaluatesTo.write(writer, instance.`object`)
        }
    }
}
