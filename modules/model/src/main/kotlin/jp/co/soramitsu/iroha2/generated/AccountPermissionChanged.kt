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
 * AccountPermissionChanged
 *
 * Generated from 'AccountPermissionChanged' regular structure
 */
public data class AccountPermissionChanged(
    public val account: AccountId,
    public val permission: PermissionId,
) {
    public companion object :
        ScaleReader<AccountPermissionChanged>,
        ScaleWriter<AccountPermissionChanged> {
        override fun read(reader: ScaleCodecReader): AccountPermissionChanged = try {
            AccountPermissionChanged(
                AccountId.read(reader),
                PermissionId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: AccountPermissionChanged): Unit = try {
            AccountId.write(writer, instance.account)
            PermissionId.write(writer, instance.permission)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
