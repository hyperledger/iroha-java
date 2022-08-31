//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAssetsByDomainIdAndAssetDefinitionId
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetsByDomainIdAndAssetDefinitionId' regular
 * structure
 */
public data class FindAssetsByDomainIdAndAssetDefinitionId(
    public val domainId: EvaluatesTo<DomainId>,
    public val assetDefinitionId: EvaluatesTo<DefinitionId>
) {
    public companion object :
        ScaleReader<FindAssetsByDomainIdAndAssetDefinitionId>,
        ScaleWriter<FindAssetsByDomainIdAndAssetDefinitionId> {
        public override fun read(reader: ScaleCodecReader): FindAssetsByDomainIdAndAssetDefinitionId =
            try {
                FindAssetsByDomainIdAndAssetDefinitionId(
                    EvaluatesTo.read(reader) as EvaluatesTo<DomainId>,
                    EvaluatesTo.read(reader) as EvaluatesTo<DefinitionId>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

        public override fun write(
            writer: ScaleCodecWriter,
            instance: FindAssetsByDomainIdAndAssetDefinitionId
        ) = try {
            EvaluatesTo.write(writer, instance.domainId)
            EvaluatesTo.write(writer, instance.assetDefinitionId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
