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
 * DoesAccountHavePermissionToken
 *
 * Generated from 'DoesAccountHavePermissionToken' regular structure
 */
public data class DoesAccountHavePermissionToken(
    public val accountId: EvaluatesTo<AccountId>,
    public val permissionToken: EvaluatesTo<PermissionToken>,
) {
    public companion object :
        ScaleReader<DoesAccountHavePermissionToken>,
        ScaleWriter<DoesAccountHavePermissionToken> {
        override fun read(reader: ScaleCodecReader): DoesAccountHavePermissionToken = try {
            DoesAccountHavePermissionToken(
                EvaluatesTo.read(reader) as EvaluatesTo<AccountId>,
                EvaluatesTo.read(reader) as EvaluatesTo<PermissionToken>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: DoesAccountHavePermissionToken): Unit =
            try {
                EvaluatesTo.write(writer, instance.accountId)
                EvaluatesTo.write(writer, instance.permissionToken)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
