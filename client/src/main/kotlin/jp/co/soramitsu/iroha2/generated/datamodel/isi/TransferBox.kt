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
    public override fun read(reader: ScaleCodecReader): TransferBox =
        TransferBox(IdBox.read(reader),
    Value.read(reader),
    IdBox.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: TransferBox): Unit {
      IdBox.write(writer, instance.sourceId)
      Value.write(writer, instance.object)
      IdBox.write(writer, instance.destinationId)
    }
  }
}
