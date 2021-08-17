//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo

/**
 * UnregisterBox
 *
 * Generated from 'iroha_data_model::isi::UnregisterBox' regular structure
 */
public data class UnregisterBox(
    public val objectId: EvaluatesTo<IdBox>
) {
    public companion object : ScaleReader<UnregisterBox>, ScaleWriter<UnregisterBox> {
        public override fun read(reader: ScaleCodecReader): UnregisterBox = UnregisterBox(
            EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
        )

        public override fun write(writer: ScaleCodecWriter, instance: UnregisterBox) {
            EvaluatesTo.write(writer, instance.objectId)
        }
    }
}
