//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableMap

/**
 * Where
 *
 * Generated from 'iroha_data_model::expression::Where' regular structure
 */
public class Where(
  public val expression: EvaluatesTo<Value>,
  public val values: MutableMap<String, EvaluatesTo<Value>>
) {
  public companion object : ScaleReader<Where>, ScaleWriter<Where> {
    public override fun read(reader: ScaleCodecReader): Where = Where(Value.read(reader),
    String.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: Where): Unit {
      Value.write(writer, instance.expression)
      String.write(writer, instance.values)
    }
  }
}
