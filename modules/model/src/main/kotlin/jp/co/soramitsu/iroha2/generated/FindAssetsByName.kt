//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAssetsByName
 *
 * Generated from 'FindAssetsByName' regular structure
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
