//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.account

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import kotlin.Unit
import kotlin.collections.MutableList

/**
 * NewAccount
 *
 * Generated from 'iroha_data_model::account::NewAccount' regular structure
 */
public class NewAccount(
  public val id: Id,
  public val signatories: MutableList<PublicKey>,
  public val metadata: Metadata
) {
  public companion object : ScaleReader<NewAccount>, ScaleWriter<NewAccount> {
    public override fun read(reader: ScaleCodecReader): NewAccount = NewAccount(
      Id.read(reader) as Id,
      MutableList(reader.readCompactInt()) {PublicKey.read(reader) as PublicKey},
      Metadata.read(reader) as Metadata,
    )

    public override fun write(writer: ScaleCodecWriter, instance: NewAccount): Unit {
        Id.write(writer, instance.id)
        writer.writeCompact(instance.signatories.size)
        instance.signatories.forEach { value -> PublicKey.write(writer, value) }
        Metadata.write(writer, instance.metadata)
    }
  }
}
