//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.peer

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Unit

/**
 * Peer
 *
 * Generated from 'iroha_data_model::peer::Peer' regular structure
 */
public class Peer(
  public val id: Id
) {
  public companion object : ScaleReader<Peer>, ScaleWriter<Peer> {
    public override fun read(reader: ScaleCodecReader): Peer = Peer(Id.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: Peer): Unit {
      Id.write(writer, instance.id)
    }
  }
}
