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
 * Mod
 *
 * Generated from 'iroha_data_model::expression::Mod' regular structure
 */
public class Mod(
  public val left: EvaluatesTo<UInt>,
  public val right: EvaluatesTo<UInt>
) {
  public companion object : ScaleReader<Mod>, ScaleWriter<Mod> {
    public override fun read(reader: ScaleCodecReader): Mod = Mod(UInt.read(reader),
    UInt.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: Mod): Unit {
      UInt.write(writer, instance.left)
      UInt.write(writer, instance.right)
    }
  }
}
