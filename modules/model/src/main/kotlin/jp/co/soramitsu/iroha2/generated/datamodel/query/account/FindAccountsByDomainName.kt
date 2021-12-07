//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.account

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String

/**
 * FindAccountsByDomainName
 *
 * Generated from 'iroha_data_model::query::account::FindAccountsByDomainName' regular structure
 */
public data class FindAccountsByDomainName(
    public val domainName: EvaluatesTo<String>
) {
    public companion object :
        ScaleReader<FindAccountsByDomainName>,
        ScaleWriter<FindAccountsByDomainName> {
        public override fun read(reader: ScaleCodecReader): FindAccountsByDomainName = try {
            FindAccountsByDomainName(
                EvaluatesTo.read(reader) as EvaluatesTo<String>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAccountsByDomainName) = try {
            EvaluatesTo.write(writer, instance.domainName)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
