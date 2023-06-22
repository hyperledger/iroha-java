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
 * FindAccountsByName
 *
 * Generated from 'FindAccountsByName' regular structure
 */
public data class FindAccountsByName(
    public val name: EvaluatesTo<Name>
) {
    public companion object : ScaleReader<FindAccountsByName>, ScaleWriter<FindAccountsByName> {
        public override fun read(reader: ScaleCodecReader): FindAccountsByName = try {
            FindAccountsByName(
                EvaluatesTo.read(reader) as EvaluatesTo<Name>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAccountsByName) = try {
            EvaluatesTo.write(writer, instance.name)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
