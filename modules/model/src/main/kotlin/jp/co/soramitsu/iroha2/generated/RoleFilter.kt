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
 * RoleFilter
 *
 * Generated from 'RoleFilter' regular structure
 */
public data class RoleFilter(
    public val originFilter: FilterOptOfOriginFilterOfRoleEvent,
    public val eventFilter: FilterOptOfRoleEventFilter
) {
    public companion object : ScaleReader<RoleFilter>, ScaleWriter<RoleFilter> {
        public override fun read(reader: ScaleCodecReader): RoleFilter = try {
            RoleFilter(
                FilterOptOfOriginFilterOfRoleEvent.read(reader),
                FilterOptOfRoleEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: RoleFilter) = try {
            FilterOptOfOriginFilterOfRoleEvent.write(writer, instance.originFilter)
            FilterOptOfRoleEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
