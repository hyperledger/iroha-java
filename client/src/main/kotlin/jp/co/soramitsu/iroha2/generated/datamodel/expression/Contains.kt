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
 * Contains
 *
 * Generated from 'iroha_data_model::expression::Contains' regular structure
 */
public class Contains(
  public val collection: EvaluatesTo<MutableList<Value>>,
  public val element: EvaluatesTo<Value>
) {
  public companion object : ScaleReader<Contains>, ScaleWriter<Contains> {
    public override fun read(reader: ScaleCodecReader): Contains = Contains(
      EvaluatesTo<MutableList<Value>>.read(reader),
      EvaluatesTo<Value>.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter, instance: Contains): Unit {
        EvaluatesTo<MutableList<Value>>.write(writer, instance.collection)
        EvaluatesTo<Value>.write(writer, instance.element)
    }
  }
}
