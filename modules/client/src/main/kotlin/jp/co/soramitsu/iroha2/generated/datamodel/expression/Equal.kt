//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import kotlin.Unit

/**
 * Equal
 *
 * Generated from 'iroha_data_model::expression::Equal' regular structure
 */
public class Equal(
  public val left: EvaluatesTo<Value>,
  public val right: EvaluatesTo<Value>
) {
  public companion object : ScaleReader<Equal>, ScaleWriter<Equal> {
    public override fun read(reader: ScaleCodecReader): Equal = Equal(
      EvaluatesTo.read(reader) as EvaluatesTo<Value>,
      EvaluatesTo.read(reader) as EvaluatesTo<Value>,
    )

    public override fun write(writer: ScaleCodecWriter, instance: Equal): Unit {
        EvaluatesTo.write(writer, instance.left)
        EvaluatesTo.write(writer, instance.right)
    }
  }
}
