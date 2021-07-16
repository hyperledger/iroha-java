//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.account

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.String
import kotlin.Unit

/**
 * FindAccountsByName
 *
 * Generated from 'iroha_data_model::query::account::FindAccountsByName' regular structure
 */
public class FindAccountsByName(
  public val name: EvaluatesTo<String>
) {
  public companion object : ScaleReader<FindAccountsByName>, ScaleWriter<FindAccountsByName> {
    public override fun read(reader: ScaleCodecReader): FindAccountsByName =
        FindAccountsByName(String.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: FindAccountsByName): Unit {
      String.write(writer, instance.name)
    }
  }
}
