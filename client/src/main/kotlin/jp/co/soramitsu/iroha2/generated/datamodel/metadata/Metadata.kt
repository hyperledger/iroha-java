//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.metadata

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableMap

/**
 * Metadata
 *
 * Generated from 'iroha_data_model::metadata::Metadata' regular structure
 */
public class Metadata(
  public val map: MutableMap<String, Value>
) {
  public companion object : ScaleReader<Metadata>, ScaleWriter<Metadata> {
    public override fun read(reader: ScaleCodecReader): Metadata =
        Metadata(jp.co.soramitsu.iroha2.scale.MapReader(jp.co.soramitsu.iroha2.scale.StringReader,
        jp.co.soramitsu.iroha2.generated.datamodel.Value).read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: Metadata): Unit {
      jp.co.soramitsu.iroha2.scale.MapWriter(jp.co.soramitsu.iroha2.scale.StringWriter,
          jp.co.soramitsu.iroha2.generated.datamodel.Value).write(writer, instance.map)
    }
  }
}
