//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String

/**
 * FindAssetsByDomainName
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetsByDomainName' regular structure
 */
public data class FindAssetsByDomainName(
    public val domainName: EvaluatesTo<String>
) {
    public companion object : ScaleReader<FindAssetsByDomainName>, ScaleWriter<FindAssetsByDomainName> {
        public override fun read(reader: ScaleCodecReader): FindAssetsByDomainName = try {
            FindAssetsByDomainName(
                EvaluatesTo.read(reader) as EvaluatesTo<String>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAssetsByDomainName) = try {
            EvaluatesTo.write(writer, instance.domainName)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
