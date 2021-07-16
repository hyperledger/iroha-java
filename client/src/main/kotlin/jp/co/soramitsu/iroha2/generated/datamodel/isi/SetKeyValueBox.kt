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
import kotlin.String
import kotlin.Unit

/**
 * SetKeyValueBox
 *
 * Generated from 'iroha_data_model::isi::SetKeyValueBox' regular structure
 */
public class SetKeyValueBox(
  public val objectId: EvaluatesTo<IdBox>,
  public val key: EvaluatesTo<String>,
  public val `value`: EvaluatesTo<Value>
) {
  public companion object : ScaleReader<SetKeyValueBox>, ScaleWriter<SetKeyValueBox> {
    public override fun read(reader: ScaleCodecReader): SetKeyValueBox =
        SetKeyValueBox(IdBox.read(reader),
    String.read(reader),
    Value.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: SetKeyValueBox): Unit {
      IdBox.write(writer, instance.objectId)
      String.write(writer, instance.key)
      Value.write(writer, instance.value)
    }
  }
}
