//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.peer

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import kotlin.String
import kotlin.Unit

/**
 * Id
 *
 * Generated from 'iroha_data_model::peer::Id' regular structure
 */
public class Id(
  public val address: String,
  public val publicKey: PublicKey
) {
  public companion object : ScaleReader<Id>, ScaleWriter<Id> {
    public override fun read(reader: ScaleCodecReader): Id = Id(
      reader.readString(),
      PublicKey.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter, instance: Id): Unit {
        writer.writeAsList(instance.address.toByteArray(Charsets.UTF_8))

    }
  }
}
