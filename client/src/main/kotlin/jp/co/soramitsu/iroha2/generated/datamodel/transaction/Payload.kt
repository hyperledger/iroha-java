//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
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
    public override fun read(reader: ScaleCodecReader): Payload = Payload(Id.read(reader),
    io.emeraldpay.polkaj.scale.reader.ListReader(jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction).read(reader),
    jp.co.soramitsu.iroha2.scale.U64Reader.read(reader),
    jp.co.soramitsu.iroha2.scale.U64Reader.read(reader),
    jp.co.soramitsu.iroha2.scale.MapReader(jp.co.soramitsu.iroha2.scale.StringReader,
        jp.co.soramitsu.iroha2.generated.datamodel.Value).read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: Payload): Unit {
      Id.write(writer, instance.accountId)
      io.emeraldpay.polkaj.scale.writer.ListWriter(jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction).write(writer,
          instance.instructions)
      jp.co.soramitsu.iroha2.scale.U64Writer.write(writer, instance.creationTime)
      jp.co.soramitsu.iroha2.scale.U64Writer.write(writer, instance.timeToLiveMs)
      jp.co.soramitsu.iroha2.scale.MapWriter(jp.co.soramitsu.iroha2.scale.StringWriter,
          jp.co.soramitsu.iroha2.generated.datamodel.Value).write(writer, instance.metadata)
    }
  }
}
