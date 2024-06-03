//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit

/**
 * FindAccountKeyValueByIdAndKey
 *
 * Generated from 'FindAccountKeyValueByIdAndKey' regular structure
 */
public data class FindAccountKeyValueByIdAndKey(
    public val id: EvaluatesTo<AccountId>,
    public val key: EvaluatesTo<Name>,
) {
    public companion object :
        ScaleReader<FindAccountKeyValueByIdAndKey>,
        ScaleWriter<FindAccountKeyValueByIdAndKey> {
        override fun read(reader: ScaleCodecReader): FindAccountKeyValueByIdAndKey = try {
            FindAccountKeyValueByIdAndKey(
                EvaluatesTo.read(reader) as EvaluatesTo<AccountId>,
                EvaluatesTo.read(reader) as EvaluatesTo<Name>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: FindAccountKeyValueByIdAndKey): Unit =
            try {
                EvaluatesTo.write(writer, instance.id)
                EvaluatesTo.write(writer, instance.key)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
