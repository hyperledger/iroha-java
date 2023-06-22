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
 * DoesAccountHavePermissionToken
 *
 * Generated from 'DoesAccountHavePermissionToken' regular structure
 */
public data class DoesAccountHavePermissionToken(
    public val accountId: EvaluatesTo<AccountId>,
    public val permissionToken: PermissionToken
) {
    public companion object :
        ScaleReader<DoesAccountHavePermissionToken>,
        ScaleWriter<DoesAccountHavePermissionToken> {
        public override fun read(reader: ScaleCodecReader): DoesAccountHavePermissionToken = try {
            DoesAccountHavePermissionToken(
                EvaluatesTo.read(reader) as EvaluatesTo<AccountId>,
                PermissionToken.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: DoesAccountHavePermissionToken) =
            try {
                EvaluatesTo.write(writer, instance.accountId)
                PermissionToken.write(writer, instance.permissionToken)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
