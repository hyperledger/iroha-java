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
 * OriginFilterEventsDataEventsRoleRoleEvent
 *
 * Generated from
 * 'iroha_data_model::events::data::filters::OriginFilterEventsDataEventsRoleRoleEvent' tuple structure
 */
public data class OriginFilterEventsDataEventsRoleRoleEvent(
    public val roleId: RoleId
) {
    public companion object :
        ScaleReader<OriginFilterEventsDataEventsRoleRoleEvent>,
        ScaleWriter<OriginFilterEventsDataEventsRoleRoleEvent> {
        public override fun read(reader: ScaleCodecReader): OriginFilterEventsDataEventsRoleRoleEvent =
            try {
                OriginFilterEventsDataEventsRoleRoleEvent(
                    RoleId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

        public override fun write(
            writer: ScaleCodecWriter,
            instance: OriginFilterEventsDataEventsRoleRoleEvent
        ) = try {
            RoleId.write(writer, instance.roleId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
