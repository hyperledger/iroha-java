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
 * FindAssetsByAccountId
 *
 * Generated from 'FindAssetsByAccountId' regular structure
 */
public data class FindAssetsByAccountId(
    public val accountId: EvaluatesTo<AccountId>,
) {
    public companion object : ScaleReader<FindAssetsByAccountId>, ScaleWriter<FindAssetsByAccountId> {
        override fun read(reader: ScaleCodecReader): FindAssetsByAccountId = try {
            FindAssetsByAccountId(
                EvaluatesTo.read(reader) as EvaluatesTo<AccountId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: FindAssetsByAccountId): Unit = try {
            EvaluatesTo.write(writer, instance.accountId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
