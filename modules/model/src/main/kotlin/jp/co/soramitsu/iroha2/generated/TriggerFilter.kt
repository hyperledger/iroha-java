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
 * TriggerFilter
 *
 * Generated from 'TriggerFilter' regular structure
 */
public data class TriggerFilter(
    public val originFilter: FilterOptOfOriginFilterOfTriggerEvent,
    public val eventFilter: FilterOptOfTriggerEventFilter,
) {
    public companion object : ScaleReader<TriggerFilter>, ScaleWriter<TriggerFilter> {
        override fun read(reader: ScaleCodecReader): TriggerFilter = try {
            TriggerFilter(
                FilterOptOfOriginFilterOfTriggerEvent.read(reader),
                FilterOptOfTriggerEventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: TriggerFilter): Unit = try {
            FilterOptOfOriginFilterOfTriggerEvent.write(writer, instance.originFilter)
            FilterOptOfTriggerEventFilter.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
