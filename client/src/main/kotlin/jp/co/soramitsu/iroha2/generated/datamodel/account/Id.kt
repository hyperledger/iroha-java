//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.account

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.String
import kotlin.Unit

/**
 * Id
 *
 * Generated from 'iroha_data_model::account::Id' regular structure
 */
public class Id(
  public val name: String,
  public val domainName: String
) {
  public companion object : ScaleReader<Id>, ScaleWriter<Id> {
    public override fun read(reader: ScaleCodecReader): Id =
        Id(jp.co.soramitsu.iroha2.scale.StringReader.read(reader),
    jp.co.soramitsu.iroha2.scale.StringReader.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: Id): Unit {
      writer.writeAsList(instance.name.encodeToByteArray())
      writer.writeAsList(instance.domainName.encodeToByteArray())
    }
  }
}
