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
 * FindDomainKeyValueByIdAndKey
 *
 * Generated from 'iroha_data_model::query::domain::FindDomainKeyValueByIdAndKey' regular structure
 */
public data class FindDomainKeyValueByIdAndKey(
    public val name: EvaluatesTo<String>,
    public val key: EvaluatesTo<String>
) {
    public companion object :
        ScaleReader<FindDomainKeyValueByIdAndKey>,
        ScaleWriter<FindDomainKeyValueByIdAndKey> {
        public override fun read(reader: ScaleCodecReader): FindDomainKeyValueByIdAndKey = try {
            FindDomainKeyValueByIdAndKey(
                EvaluatesTo.read(reader) as EvaluatesTo<String>,
                EvaluatesTo.read(reader) as EvaluatesTo<String>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindDomainKeyValueByIdAndKey) =
            try {
                EvaluatesTo.write(writer, instance.name)
                EvaluatesTo.write(writer, instance.key)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
