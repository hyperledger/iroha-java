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
 * FindPermissionTokensByAccountId
 *
 * Generated from 'FindPermissionTokensByAccountId' regular structure
 */
public data class FindPermissionTokensByAccountId(
    public val id: EvaluatesTo<AccountId>,
) {
    public companion object :
        ScaleReader<FindPermissionTokensByAccountId>,
        ScaleWriter<FindPermissionTokensByAccountId> {
        override fun read(reader: ScaleCodecReader): FindPermissionTokensByAccountId = try {
            FindPermissionTokensByAccountId(
                EvaluatesTo.read(reader) as EvaluatesTo<AccountId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: FindPermissionTokensByAccountId): Unit =
            try {
                EvaluatesTo.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
