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
    public val originFilter: FilterOpt<OriginFilter<RoleEvent>>,
    public val eventFilter: FilterOpt<RoleEventFilter>
) {
    public companion object : ScaleReader<RoleFilter>, ScaleWriter<RoleFilter> {
        public override fun read(reader: ScaleCodecReader): RoleFilter = try {
            RoleFilter(
                FilterOpt.read(reader) as FilterOpt<OriginFilter<RoleEvent>>,
                FilterOpt.read(reader) as FilterOpt<RoleEventFilter>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: RoleFilter) = try {
            FilterOpt.write(writer, instance.originFilter)
            FilterOpt.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
