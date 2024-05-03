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
 * GrantOfPermissionTokenAndRole
 *
 * Generated from 'GrantOfPermissionTokenAndRole' regular structure
 */
public data class GrantOfPermissionTokenAndRole(
    public val `object`: PermissionToken,
    public val destinationId: RoleId,
) {
    public companion object :
        ScaleReader<GrantOfPermissionTokenAndRole>,
        ScaleWriter<GrantOfPermissionTokenAndRole> {
        override fun read(reader: ScaleCodecReader): GrantOfPermissionTokenAndRole = try {
            GrantOfPermissionTokenAndRole(
                PermissionToken.read(reader),
                RoleId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: GrantOfPermissionTokenAndRole): Unit =
            try {
                PermissionToken.write(writer, instance.`object`)
                RoleId.write(writer, instance.destinationId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
