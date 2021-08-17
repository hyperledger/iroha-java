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
public data class Contains(
  public val collection: EvaluatesTo<MutableList<Value>>,
  public val element: EvaluatesTo<Value>
) {
  public companion object : ScaleReader<Contains>, ScaleWriter<Contains> {
    public override fun read(reader: ScaleCodecReader): Contains = Contains(
      EvaluatesTo.read(reader) as EvaluatesTo<MutableList<Value>>,
      EvaluatesTo.read(reader) as EvaluatesTo<Value>,
    )

    public override fun write(writer: ScaleCodecWriter, instance: Contains): Unit {
        EvaluatesTo.write(writer, instance.collection)
        EvaluatesTo.write(writer, instance.element)
    }
  }
}
