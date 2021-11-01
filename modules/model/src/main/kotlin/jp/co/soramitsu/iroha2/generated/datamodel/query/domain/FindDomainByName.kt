//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.domain

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String

/**
 * FindDomainByName
 *
 * Generated from 'iroha_data_model::query::domain::FindDomainByName' regular structure
 */
public data class FindDomainByName(
    public val name: EvaluatesTo<String>
) {
    public companion object : ScaleReader<FindDomainByName>, ScaleWriter<FindDomainByName> {
        public override fun read(reader: ScaleCodecReader): FindDomainByName = try {
            FindDomainByName(
                EvaluatesTo.read(reader) as EvaluatesTo<String>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindDomainByName) = try {
            EvaluatesTo.write(writer, instance.name)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
