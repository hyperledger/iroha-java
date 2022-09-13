//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId
import jp.co.soramitsu.iroha2.wrapException

/**
 * OriginFilterTriggerEvent
 *
 * Generated from 'iroha_data_model::events::data::filters::OriginFilterTriggerEvent' tuple
 * structure
 */
public data class OriginFilterTriggerEvent(
    public val triggerId: TriggerId
) {
    public companion object :
        ScaleReader<OriginFilterTriggerEvent>,
        ScaleWriter<OriginFilterTriggerEvent> {
        public override fun read(reader: ScaleCodecReader): OriginFilterTriggerEvent = try {
            OriginFilterTriggerEvent(
                TriggerId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: OriginFilterTriggerEvent) = try {
            TriggerId.write(writer, instance.triggerId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
