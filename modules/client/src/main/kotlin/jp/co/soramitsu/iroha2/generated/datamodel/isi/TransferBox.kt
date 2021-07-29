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
import kotlin.Unit

/**
 * TransferBox
 *
 * Generated from 'iroha_data_model::isi::TransferBox' regular structure
 */
public class TransferBox(
  public val sourceId: EvaluatesTo<IdBox>,
  public val `object`: EvaluatesTo<Value>,
  public val destinationId: EvaluatesTo<IdBox>
) {
  public companion object : ScaleReader<TransferBox>, ScaleWriter<TransferBox> {
    public override fun read(reader: ScaleCodecReader): TransferBox = TransferBox(
      EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
      EvaluatesTo.read(reader) as EvaluatesTo<Value>,
      EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
    )

    public override fun write(writer: ScaleCodecWriter, instance: TransferBox): Unit {
        EvaluatesTo.write(writer, instance.sourceId)
        EvaluatesTo.write(writer, instance.`object`)
        EvaluatesTo.write(writer, instance.destinationId)
    }
  }
}
