//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.role

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.permission.token.TokenId
import jp.co.soramitsu.iroha2.generated.datamodel.role.RoleId
import jp.co.soramitsu.iroha2.wrapException

/**
 * PermissionRemoved
 *
 * Generated from 'iroha_data_model::events::data::events::role::PermissionRemoved' regular
 * structure
 */
public data class PermissionRemoved(
    public val roleId: RoleId,
    public val permissionDefinitionId: TokenId
) {
    public companion object : ScaleReader<PermissionRemoved>, ScaleWriter<PermissionRemoved> {
        public override fun read(reader: ScaleCodecReader): PermissionRemoved = try {
            PermissionRemoved(
                RoleId.read(reader),
                TokenId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PermissionRemoved) = try {
            RoleId.write(writer, instance.roleId)
            TokenId.write(writer, instance.permissionDefinitionId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
