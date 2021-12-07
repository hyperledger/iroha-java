//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String

/**
 * FindAssetsByDomainNameAndAssetDefinitionId
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetsByDomainNameAndAssetDefinitionId'
 * regular structure
 */
public data class FindAssetsByDomainNameAndAssetDefinitionId(
    public val domainName: EvaluatesTo<String>,
    public val assetDefinitionId: EvaluatesTo<DefinitionId>
) {
    public companion object :
        ScaleReader<FindAssetsByDomainNameAndAssetDefinitionId>,
        ScaleWriter<FindAssetsByDomainNameAndAssetDefinitionId> {
        public override fun read(reader: ScaleCodecReader): FindAssetsByDomainNameAndAssetDefinitionId =
            try {
                FindAssetsByDomainNameAndAssetDefinitionId(
                    EvaluatesTo.read(reader) as EvaluatesTo<String>,
                    EvaluatesTo.read(reader) as EvaluatesTo<DefinitionId>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

        public override fun write(
            writer: ScaleCodecWriter,
            instance: FindAssetsByDomainNameAndAssetDefinitionId
        ) = try {
            EvaluatesTo.write(writer, instance.domainName)
            EvaluatesTo.write(writer, instance.assetDefinitionId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
