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
 * OriginFilterOfRoleEvent
 *
 * Generated from 'OriginFilterOfRoleEvent' regular structure
 */
public data class OriginFilterOfRoleEvent(
    public val roleId: RoleId,
) {
    public companion object :
        ScaleReader<OriginFilterOfRoleEvent>,
        ScaleWriter<OriginFilterOfRoleEvent> {
        override fun read(reader: ScaleCodecReader): OriginFilterOfRoleEvent = try {
            OriginFilterOfRoleEvent(
                RoleId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: OriginFilterOfRoleEvent): Unit = try {
            RoleId.write(writer, instance.roleId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
