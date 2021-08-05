//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.genesis

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import kotlin.Unit
import kotlin.collections.MutableList

/**
 * GenesisTransaction
 *
 * Generated from 'iroha::genesis::GenesisTransaction' regular structure
 */
public class GenesisTransaction(
  public val isi: MutableList<Instruction>
) {
  public companion object : ScaleReader<GenesisTransaction>, ScaleWriter<GenesisTransaction> {
    public override fun read(reader: ScaleCodecReader): GenesisTransaction = GenesisTransaction(
      MutableList(reader.readCompactInt()) {Instruction.read(reader) as Instruction},
    )

    public override fun write(writer: ScaleCodecWriter, instance: GenesisTransaction): Unit {
        writer.writeCompact(instance.isi.size)
        instance.isi.forEach { value -> Instruction.write(writer, value) }
    }
  }
}
