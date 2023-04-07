//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.role.RoleId
import jp.co.soramitsu.iroha2.wrapException

/**
 * OriginFilterRoleEvent
 *
 * Generated from 'iroha_data_model::events::data::filters::OriginFilterRoleEvent' tuple structure
 */
public data class OriginFilterRoleEvent(
    public val roleId: RoleId
) {
    public companion object : ScaleReader<OriginFilterRoleEvent>, ScaleWriter<OriginFilterRoleEvent> {
        public override fun read(reader: ScaleCodecReader): OriginFilterRoleEvent = try {
            OriginFilterRoleEvent(
                RoleId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: OriginFilterRoleEvent) = try {
            RoleId.write(writer, instance.roleId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
