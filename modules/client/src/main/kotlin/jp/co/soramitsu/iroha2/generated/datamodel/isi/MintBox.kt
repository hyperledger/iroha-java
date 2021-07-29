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
 * MintBox
 *
 * Generated from 'iroha_data_model::isi::MintBox' regular structure
 */
public class MintBox(
  public val `object`: EvaluatesTo<Value>,
  public val destinationId: EvaluatesTo<IdBox>
) {
  public companion object : ScaleReader<MintBox>, ScaleWriter<MintBox> {
    public override fun read(reader: ScaleCodecReader): MintBox = MintBox(
      EvaluatesTo.read(reader) as EvaluatesTo<Value>,
      EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
    )

    public override fun write(writer: ScaleCodecWriter, instance: MintBox): Unit {
        EvaluatesTo.write(writer, instance.`object`)
        EvaluatesTo.write(writer, instance.destinationId)
    }
  }
}
