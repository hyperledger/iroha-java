//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.String
import kotlin.Unit

/**
 * FindAssetsByDomainNameAndAssetDefinitionId
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetsByDomainNameAndAssetDefinitionId'
 * regular structure
 */
public class FindAssetsByDomainNameAndAssetDefinitionId(
  public val domainName: EvaluatesTo<String>,
  public val assetDefinitionId: EvaluatesTo<DefinitionId>
) {
  public companion object : ScaleReader<FindAssetsByDomainNameAndAssetDefinitionId>,
      ScaleWriter<FindAssetsByDomainNameAndAssetDefinitionId> {
    public override fun read(reader: ScaleCodecReader): FindAssetsByDomainNameAndAssetDefinitionId =
        FindAssetsByDomainNameAndAssetDefinitionId(
      EvaluatesTo<String>.read(reader),
      EvaluatesTo<DefinitionId>.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter,
        instance: FindAssetsByDomainNameAndAssetDefinitionId): Unit {


    }
  }
}
