//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAssetsByName
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetsByName' regular structure
 */
public data class FindAssetsByName(
    public val name: EvaluatesTo<Name>
) {
    public companion object : ScaleReader<FindAssetsByName>, ScaleWriter<FindAssetsByName> {
        public override fun read(reader: ScaleCodecReader): FindAssetsByName = try {
            FindAssetsByName(
                EvaluatesTo.read(reader) as EvaluatesTo<Name>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAssetsByName) = try {
            EvaluatesTo.write(writer, instance.name)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
