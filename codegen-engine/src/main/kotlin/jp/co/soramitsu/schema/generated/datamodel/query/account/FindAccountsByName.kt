// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.query.account

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo
import kotlin.Unit

/**
 * FindAccountsByName
 *
 * Generated from 'iroha_data_model::query::account::FindAccountsByName' regular structure
 */
public class FindAccountsByName(
  private val name: EvaluatesTo
) {
  public companion object : ScaleReader<FindAccountsByName>, ScaleWriter<FindAccountsByName> {
    public override fun read(reader: ScaleCodecReader): FindAccountsByName =
        FindAccountsByName(jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: FindAccountsByName): Unit {
      jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.write(writer,
          instance.`name`)
    }
  }
}
