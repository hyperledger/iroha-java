//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.String
import kotlin.Unit

/**
 * FindAssetsByDomainName
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetsByDomainName' regular structure
 */
public class FindAssetsByDomainName(
  public val domainName: EvaluatesTo<String>
) {
  public companion object : ScaleReader<FindAssetsByDomainName>, ScaleWriter<FindAssetsByDomainName>
      {
    public override fun read(reader: ScaleCodecReader): FindAssetsByDomainName =
        FindAssetsByDomainName(
      EvaluatesTo<String>.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter, instance: FindAssetsByDomainName): Unit {
        EvaluatesTo<String>.write(writer, instance.domainName)
    }
  }
}
