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
 * RolePermissionChanged
 *
 * Generated from 'RolePermissionChanged' regular structure
 */
public data class RolePermissionChanged(
    public val role: RoleId,
    public val permission: PermissionId,
) {
    public companion object : ScaleReader<RolePermissionChanged>, ScaleWriter<RolePermissionChanged> {
        override fun read(reader: ScaleCodecReader): RolePermissionChanged = try {
            RolePermissionChanged(
                RoleId.read(reader),
                PermissionId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: RolePermissionChanged): Unit = try {
            RoleId.write(writer, instance.role)
            PermissionId.write(writer, instance.permission)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
