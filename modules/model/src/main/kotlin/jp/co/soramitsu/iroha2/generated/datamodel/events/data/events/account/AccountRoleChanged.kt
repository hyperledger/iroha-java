//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.account

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.role.RoleId
import jp.co.soramitsu.iroha2.wrapException

/**
 * AccountRoleChanged
 *
 * Generated from 'iroha_data_model::events::data::events::account::AccountRoleChanged' regular
 * structure
 */
public data class AccountRoleChanged(
    public val accountId: AccountId,
    public val roleId: RoleId
) {
    public companion object : ScaleReader<AccountRoleChanged>, ScaleWriter<AccountRoleChanged> {
        public override fun read(reader: ScaleCodecReader): AccountRoleChanged = try {
            AccountRoleChanged(
                AccountId.read(reader),
                RoleId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AccountRoleChanged) = try {
            AccountId.write(writer, instance.accountId)
            RoleId.write(writer, instance.roleId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
