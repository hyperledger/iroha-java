//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import java.math.BigInteger
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import kotlin.Unit

/**
 * Payload
 *
 * Generated from 'iroha_data_model::query::Payload' regular structure
 */
public class Payload(
  public val timestampMs: BigInteger,
  public val query: QueryBox,
  public val accountId: Id
) {
  public companion object : ScaleReader<Payload>, ScaleWriter<Payload> {
    public override fun read(reader: ScaleCodecReader): Payload = Payload(
      reader.readCompactInt().toBigInteger(),
      QueryBox.read(reader) as QueryBox,
      Id.read(reader) as Id,
    )

    public override fun write(writer: ScaleCodecWriter, instance: Payload): Unit {
        writer.writeCompact(instance.timestampMs.toInt())
        QueryBox.write(writer, instance.query)
        Id.write(writer, instance.accountId)
    }
  }
}
