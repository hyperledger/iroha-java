//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter

/**
 * Pair
 *
 * Generated from 'iroha_data_model::isi::Pair' regular structure
 */
public class Pair(
    public val leftInstruction: Instruction,
    public val rightInstruction: Instruction
) {
    public companion object : ScaleReader<Pair>, ScaleWriter<Pair> {
        public override fun read(reader: ScaleCodecReader): Pair = Pair(
            Instruction.read(reader) as Instruction,
            Instruction.read(reader) as Instruction,
        )

        public override fun write(writer: ScaleCodecWriter, instance: Pair) {
            Instruction.write(writer, instance.leftInstruction)
            Instruction.write(writer, instance.rightInstruction)
        }
    }
}
