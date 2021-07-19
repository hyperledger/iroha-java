//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.Boolean
import kotlin.Unit

/**
 * If
 *
 * Generated from 'iroha_data_model::isi::If' regular structure
 */
public class If(
  public val condition: EvaluatesTo<Boolean>,
  public val then: Instruction,
  public val otherwise: Instruction?
) {
  public companion object : ScaleReader<If>, ScaleWriter<If> {
    public override fun read(reader: ScaleCodecReader): If = If(Boolean.read(reader),
    Instruction.read(reader),
    jp.co.soramitsu.iroha2.scale.OptionReader(jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction?).read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: If): Unit {
      Boolean.write(writer, instance.condition)
      Instruction.write(writer, instance.then)
      jp.co.soramitsu.iroha2.scale.OptionWriter(jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction?).write(writer,
          instance.otherwise)
    }
  }
}
