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
 * BurnBox
 *
 * Generated from 'iroha_data_model::isi::BurnBox' regular structure
 */
public class BurnBox(
  public val `object`: EvaluatesTo<Value>,
  public val destinationId: EvaluatesTo<IdBox>
) {
  public companion object : ScaleReader<BurnBox>, ScaleWriter<BurnBox> {
    public override fun read(reader: ScaleCodecReader): BurnBox = BurnBox(Value.read(reader),
    IdBox.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: BurnBox): Unit {
      Value.write(writer, instance.object)
      IdBox.write(writer, instance.destinationId)
    }
  }
}
