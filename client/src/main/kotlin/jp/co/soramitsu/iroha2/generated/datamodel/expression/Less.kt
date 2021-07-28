//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.UInt
import kotlin.Unit

/**
 * Less
 *
 * Generated from 'iroha_data_model::expression::Less' regular structure
 */
public class Less(
  public val left: EvaluatesTo<UInt>,
  public val right: EvaluatesTo<UInt>
) {
  public companion object : ScaleReader<Less>, ScaleWriter<Less> {
    public override fun read(reader: ScaleCodecReader): Less = Less(
      EvaluatesTo<UInt>.read(reader),
      EvaluatesTo<UInt>.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter, instance: Less): Unit {
        EvaluatesTo<UInt>.write(writer, instance.left)
        EvaluatesTo<UInt>.write(writer, instance.right)
    }
  }
}
