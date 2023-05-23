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
 * PermissionRemoved
 *
 * Generated from 'PermissionRemoved' regular structure
 */
public data class PermissionRemoved(
    public val roleId: RoleId,
    public val permissionDefinitionId: PermissionTokenId
) {
    public companion object : ScaleReader<PermissionRemoved>, ScaleWriter<PermissionRemoved> {
        public override fun read(reader: ScaleCodecReader): PermissionRemoved = try {
            PermissionRemoved(
                RoleId.read(reader),
                PermissionTokenId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PermissionRemoved) = try {
            RoleId.write(writer, instance.roleId)
            PermissionTokenId.write(writer, instance.permissionDefinitionId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
