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
 * OriginFilterOfRoleEvent
 *
 * Generated from 'OriginFilterOfRoleEvent' regular structure
 */
public data class OriginFilterOfRoleEvent(
    public val roleId: RoleId
) {
    public companion object :
        ScaleReader<OriginFilterOfRoleEvent>,
        ScaleWriter<OriginFilterOfRoleEvent> {
        public override fun read(reader: ScaleCodecReader): OriginFilterOfRoleEvent = try {
            OriginFilterOfRoleEvent(
                RoleId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: OriginFilterOfRoleEvent) = try {
            RoleId.write(writer, instance.roleId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
