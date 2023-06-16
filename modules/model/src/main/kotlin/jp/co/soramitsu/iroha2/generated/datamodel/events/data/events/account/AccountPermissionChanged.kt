//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.account

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.permission.token.TokenId
import jp.co.soramitsu.iroha2.wrapException

/**
 * AccountPermissionChanged
 *
 * Generated from 'iroha_data_model::events::data::events::account::AccountPermissionChanged'
 * regular structure
 */
public data class AccountPermissionChanged(
    public val accountId: AccountId,
    public val permissionId: TokenId
) {
    public companion object :
        ScaleReader<AccountPermissionChanged>,
        ScaleWriter<AccountPermissionChanged> {
        public override fun read(reader: ScaleCodecReader): AccountPermissionChanged = try {
            AccountPermissionChanged(
                AccountId.read(reader),
                TokenId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AccountPermissionChanged) = try {
            AccountId.write(writer, instance.accountId)
            TokenId.write(writer, instance.permissionId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
