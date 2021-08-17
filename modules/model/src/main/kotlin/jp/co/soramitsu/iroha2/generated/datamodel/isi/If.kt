//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import java.util.Optional
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.Boolean
import kotlin.Unit

/**
 * If
 *
 * Generated from 'iroha_data_model::isi::If' regular structure
 */
public data class If(
  public val condition: EvaluatesTo<Boolean>,
  public val then: Instruction,
  public val otherwise: Instruction?
) {
  public companion object : ScaleReader<If>, ScaleWriter<If> {
    public override fun read(reader: ScaleCodecReader): If = If(
      EvaluatesTo.read(reader) as EvaluatesTo<Boolean>,
      Instruction.read(reader) as Instruction,
      reader.readOptional(Instruction).orElse(null),
    )

    public override fun write(writer: ScaleCodecWriter, instance: If): Unit {
        EvaluatesTo.write(writer, instance.condition)
        Instruction.write(writer, instance.then)
        writer.writeOptional(Instruction, Optional.ofNullable(instance.otherwise))
    }
  }
}
