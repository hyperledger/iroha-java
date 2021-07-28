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
 * RaiseTo
 *
 * Generated from 'iroha_data_model::expression::RaiseTo' regular structure
 */
public class RaiseTo(
  public val left: EvaluatesTo<UInt>,
  public val right: EvaluatesTo<UInt>
) {
  public companion object : ScaleReader<RaiseTo>, ScaleWriter<RaiseTo> {
    public override fun read(reader: ScaleCodecReader): RaiseTo = RaiseTo(
      EvaluatesTo<UInt>.read(reader),
      EvaluatesTo<UInt>.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter, instance: RaiseTo): Unit {


    }
  }
}
