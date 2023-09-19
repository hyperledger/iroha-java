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
    public val accountId: AccountId,
    public val permissionId: Name,
) {
    public companion object :
        ScaleReader<AccountPermissionChanged>,
        ScaleWriter<AccountPermissionChanged> {
        override fun read(reader: ScaleCodecReader): AccountPermissionChanged = try {
            AccountPermissionChanged(
                AccountId.read(reader),
                Name.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: AccountPermissionChanged): Unit = try {
            AccountId.write(writer, instance.accountId)
            Name.write(writer, instance.permissionId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
