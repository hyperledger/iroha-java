//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Boolean
import kotlin.Unit

/**
 * Not
 *
 * Generated from 'iroha_data_model::expression::Not' regular structure
 */
public class Not(
  public val expression: EvaluatesTo<Boolean>
) {
  public companion object : ScaleReader<Not>, ScaleWriter<Not> {
    public override fun read(reader: ScaleCodecReader): Not = Not(Boolean.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: Not): Unit {
      Boolean.write(writer, instance.expression)
    }
  }
}
