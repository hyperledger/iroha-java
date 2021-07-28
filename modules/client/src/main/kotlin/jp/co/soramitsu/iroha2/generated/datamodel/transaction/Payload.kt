//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import java.math.BigInteger
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.utils.hashMapWithSize
import kotlin.String
import kotlin.ULong
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.collections.MutableMap

/**
 * Payload
 *
 * Generated from 'iroha_data_model::transaction::Payload' regular structure
 */
public class Payload(
  public val accountId: Id,
  public val instructions: MutableList<Instruction>,
  public val creationTime: ULong,
  public val timeToLiveMs: ULong,
  public val metadata: MutableMap<String, Value>
) {
  public companion object : ScaleReader<Payload>, ScaleWriter<Payload> {
    public override fun read(reader: ScaleCodecReader): Payload = Payload(
      Id.read(reader) as Id,
      MutableList(reader.readCompactInt()) {Instruction.read(reader) as Instruction},
      reader.readUint128().toLong().toULong(),
      reader.readUint128().toLong().toULong(),
      hashMapWithSize(reader.readCompactInt(), {reader.readString()}, {Value.read(reader) as
          Value}),
    )

    public override fun write(writer: ScaleCodecWriter, instance: Payload): Unit {
        Id.write(writer, instance.accountId)
        writer.writeCompact(instance.instructions.size)
        instance.instructions.forEach { value -> Instruction.write(writer, value) }
        writer.writeUint128(BigInteger.valueOf(instance.creationTime.toLong()))
        writer.writeUint128(BigInteger.valueOf(instance.timeToLiveMs.toLong()))
        writer.writeCompact(instance.metadata.size)
        instance.metadata.forEach { (key, value) ->  
        	writer.writeAsList(key.toByteArray(Charsets.UTF_8))
        	Value.write(writer, value)
        }
    }
  }
}
