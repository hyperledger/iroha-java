// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.account

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.schema.generated.crypto.PublicKey
import jp.co.soramitsu.schema.generated.datamodel.metadata.Metadata
import kotlin.Unit
import kotlin.collections.List

/**
 * NewAccount
 *
 * Generated from 'iroha_data_model::account::NewAccount' regular structure
 */
public class NewAccount(
  private val id: Id,
  private val signatories: List<PublicKey>,
  private val metadata: Metadata
) : ScaleReader<NewAccount>, ScaleWriter<NewAccount> {
  public override fun read(reader: ScaleCodecReader): NewAccount = NewAccount(id.read(reader),
      signatories.read(reader), metadata.read(reader))

  public override fun write(writer: ScaleCodecWriter, instance: NewAccount): Unit {
    id.write(writer, instance.id),
    signatories.write(writer, instance.signatories),
    metadata.write(writer, instance.metadata)
  }
}
