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
import kotlin.collections.MutableList

/**
 * ContainsAny
 *
 * Generated from 'iroha_data_model::expression::ContainsAny' regular structure
 */
public class ContainsAny(
  public val collection: EvaluatesTo<MutableList<Value>>,
  public val elements: EvaluatesTo<MutableList<Value>>
) {
  public companion object : ScaleReader<ContainsAny>, ScaleWriter<ContainsAny> {
    public override fun read(reader: ScaleCodecReader): ContainsAny = ContainsAny(
      EvaluatesTo<MutableList<Value>>.read(reader),
      EvaluatesTo<MutableList<Value>>.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter, instance: ContainsAny): Unit {


    }
  }
}
