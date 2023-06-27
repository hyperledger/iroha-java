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
 * AccountPermissionChanged
 *
 * Generated from 'AccountPermissionChanged' regular structure
 */
public data class AccountPermissionChanged(
    public val accountId: AccountId,
    public val permissionId: PermissionTokenId
) {
    public companion object :
        ScaleReader<AccountPermissionChanged>,
        ScaleWriter<AccountPermissionChanged> {
        public override fun read(reader: ScaleCodecReader): AccountPermissionChanged = try {
            AccountPermissionChanged(
                AccountId.read(reader),
                PermissionTokenId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AccountPermissionChanged) = try {
            AccountId.write(writer, instance.accountId)
            PermissionTokenId.write(writer, instance.permissionId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
