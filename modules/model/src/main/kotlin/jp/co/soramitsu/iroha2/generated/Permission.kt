//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String
import kotlin.Unit

/**
 * Permission
 *
 * Generated from 'Permission' regular structure
 */
public data class Permission(
    public val id: PermissionId,
    public val payload: String,
) {
    public companion object : ScaleReader<Permission>, ScaleWriter<Permission> {
        override fun read(reader: ScaleCodecReader): Permission = try {
            Permission(
                PermissionId.read(reader),
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: Permission): Unit = try {
            PermissionId.write(writer, instance.id)
            writer.writeAsList(instance.payload.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
