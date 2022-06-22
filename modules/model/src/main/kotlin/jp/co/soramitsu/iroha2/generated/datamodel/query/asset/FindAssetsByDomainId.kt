//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAssetsByDomainId
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetsByDomainId' regular structure
 */
public data class FindAssetsByDomainId(
    public val domainId: EvaluatesTo<DomainId>
) {
    public companion object : ScaleReader<FindAssetsByDomainId>, ScaleWriter<FindAssetsByDomainId> {
        public override fun read(reader: ScaleCodecReader): FindAssetsByDomainId = try {
            FindAssetsByDomainId(
                EvaluatesTo.read(reader) as EvaluatesTo<DomainId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAssetsByDomainId) = try {
            EvaluatesTo.write(writer, instance.domainId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
