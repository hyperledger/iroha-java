//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.domain

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindDomainById
 *
 * Generated from 'iroha_data_model::query::domain::FindDomainById' regular structure
 */
public data class FindDomainById(
    public val id: EvaluatesTo<DomainId>
) {
    public companion object : ScaleReader<FindDomainById>, ScaleWriter<FindDomainById> {
        public override fun read(reader: ScaleCodecReader): FindDomainById = try {
            FindDomainById(
                EvaluatesTo.read(reader) as EvaluatesTo<DomainId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindDomainById) = try {
            EvaluatesTo.write(writer, instance.id)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
