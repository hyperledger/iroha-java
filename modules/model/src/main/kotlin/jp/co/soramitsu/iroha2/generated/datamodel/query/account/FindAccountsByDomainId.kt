//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.account

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Id
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAccountsByDomainId
 *
 * Generated from 'iroha_data_model::query::account::FindAccountsByDomainId' regular structure
 */
public data class FindAccountsByDomainId(
    public val domainId: EvaluatesTo<Id>
) {
    public companion object : ScaleReader<FindAccountsByDomainId>, ScaleWriter<FindAccountsByDomainId> {
        public override fun read(reader: ScaleCodecReader): FindAccountsByDomainId = try {
            FindAccountsByDomainId(
                EvaluatesTo.read(reader) as EvaluatesTo<Id>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAccountsByDomainId) = try {
            EvaluatesTo.write(writer, instance.domainId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
