// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.query.account

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo
import kotlin.Unit

/**
 * FindAccountsByDomainName
 *
 * Generated from 'iroha_data_model::query::account::FindAccountsByDomainName' regular structure
 */
public class FindAccountsByDomainName(
  private val domainName: EvaluatesTo
) : ScaleReader<FindAccountsByDomainName>, ScaleWriter<FindAccountsByDomainName> {
  public override fun read(reader: ScaleCodecReader): FindAccountsByDomainName =
      FindAccountsByDomainName(domainName.read(reader))

  public override fun write(writer: ScaleCodecWriter, instance: FindAccountsByDomainName): Unit {
    domainName.write(writer, instance.domainName)
  }
}
