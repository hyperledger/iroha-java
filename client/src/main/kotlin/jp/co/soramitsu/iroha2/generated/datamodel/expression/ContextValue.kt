//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.String
import kotlin.Unit

/**
 * ContextValue
 *
 * Generated from 'iroha_data_model::expression::ContextValue' regular structure
 */
public class ContextValue(
  public val valueName: String
) {
  public companion object : ScaleReader<ContextValue>, ScaleWriter<ContextValue> {
    public override fun read(reader: ScaleCodecReader): ContextValue =
        ContextValue(jp.co.soramitsu.iroha2.scale.StringReader.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: ContextValue): Unit {
      writer.writeAsList(instance.valueName.encodeToByteArray())
    }
  }
}
