//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.String
import kotlin.Unit

/**
 * DefinitionId
 *
 * Generated from 'iroha_data_model::asset::DefinitionId' regular structure
 */
public class DefinitionId(
  public val name: String,
  public val domainName: String
) {
  public companion object : ScaleReader<DefinitionId>, ScaleWriter<DefinitionId> {
    public override fun read(reader: ScaleCodecReader): DefinitionId =
        DefinitionId(jp.co.soramitsu.iroha2.scale.StringReader.read(reader),
    jp.co.soramitsu.iroha2.scale.StringReader.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: DefinitionId): Unit {
      jp.co.soramitsu.iroha2.scale.StringWriter.write(writer, instance.name)
      jp.co.soramitsu.iroha2.scale.StringWriter.write(writer, instance.domainName)
    }
  }
}
