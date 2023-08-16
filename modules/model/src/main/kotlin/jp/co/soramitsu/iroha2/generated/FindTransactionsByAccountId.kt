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
 * FindTransactionsByAccountId
 *
 * Generated from 'FindTransactionsByAccountId' regular structure
 */
public data class FindTransactionsByAccountId(
    public val accountId: EvaluatesTo<AccountId>,
) {
    public companion object :
        ScaleReader<FindTransactionsByAccountId>,
        ScaleWriter<FindTransactionsByAccountId> {
        override fun read(reader: ScaleCodecReader): FindTransactionsByAccountId = try {
            FindTransactionsByAccountId(
                EvaluatesTo.read(reader) as EvaluatesTo<AccountId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: FindTransactionsByAccountId): Unit = try {
            EvaluatesTo.write(writer, instance.accountId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
