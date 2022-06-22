//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.role

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.comparator
import jp.co.soramitsu.iroha2.generated.datamodel.permissions.PermissionToken
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List

/**
 * Role
 *
 * Generated from 'iroha_data_model::role::Role' regular structure
 */
public data class Role(
    public val id: RoleId,
    public val permissions: List<PermissionToken>
) {
    public companion object : ScaleReader<Role>, ScaleWriter<Role> {
        public override fun read(reader: ScaleCodecReader): Role = try {
            Role(
                RoleId.read(reader),
                reader.readVec(reader.readCompactInt()) { PermissionToken.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Role) = try {
            RoleId.write(writer, instance.id)
            writer.writeCompact(instance.permissions.size)
            instance.permissions.sortedWith(
                PermissionToken.comparator()
            ).forEach { value ->
                PermissionToken.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
