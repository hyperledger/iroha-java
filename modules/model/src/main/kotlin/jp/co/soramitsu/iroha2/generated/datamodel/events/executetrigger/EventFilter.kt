//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.executetrigger

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id
import jp.co.soramitsu.iroha2.wrapException

/**
 * EventFilter
 *
 * Generated from 'iroha_data_model::events::execute_trigger::EventFilter' regular structure
 */
public data class EventFilter(
    public val triggerId: Id,
    public val authority: jp.co.soramitsu.iroha2.generated.datamodel.account.Id
) {
    public companion object : ScaleReader<EventFilter>, ScaleWriter<EventFilter> {
        public override fun read(reader: ScaleCodecReader): EventFilter = try {
            EventFilter(
                Id.read(reader),
                jp.co.soramitsu.iroha2.generated.datamodel.account.Id.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: EventFilter) = try {
            Id.write(writer, instance.triggerId)
            jp.co.soramitsu.iroha2.generated.datamodel.account.Id.write(writer, instance.authority)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
