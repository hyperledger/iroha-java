//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * BurnBox
 *
 * Generated from 'iroha_data_model::isi::BurnBox' regular structure
 */
public data class BurnBox(
    public val `object`: EvaluatesTo<Value>,
    public val destinationId: EvaluatesTo<IdBox>
) {
    public companion object : ScaleReader<BurnBox>, ScaleWriter<BurnBox> {
        public override fun read(reader: ScaleCodecReader): BurnBox = try {
            BurnBox(
                EvaluatesTo.read(reader) as EvaluatesTo<Value>,
                EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: BurnBox) = try {
            EvaluatesTo.write(writer, instance.`object`)
            EvaluatesTo.write(writer, instance.destinationId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
