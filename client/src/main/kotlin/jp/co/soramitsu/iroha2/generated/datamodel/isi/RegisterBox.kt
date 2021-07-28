//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.Unit

/**
 * RegisterBox
 *
 * Generated from 'iroha_data_model::isi::RegisterBox' regular structure
 */
public class RegisterBox(
  public val `object`: EvaluatesTo<IdentifiableBox>
) {
  public companion object : ScaleReader<RegisterBox>, ScaleWriter<RegisterBox> {
    public override fun read(reader: ScaleCodecReader): RegisterBox = RegisterBox(
      EvaluatesTo.read(reader) as EvaluatesTo<IdentifiableBox>,
    )

    public override fun write(writer: ScaleCodecWriter, instance: RegisterBox): Unit {
        EvaluatesTo.write(writer, instance.object)
    }
  }
}
