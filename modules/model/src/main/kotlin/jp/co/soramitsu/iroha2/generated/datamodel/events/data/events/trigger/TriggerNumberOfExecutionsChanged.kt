//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.trigger

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Long

/**
 * TriggerNumberOfExecutionsChanged
 *
 * Generated from
 * 'iroha_data_model::events::data::events::trigger::TriggerNumberOfExecutionsChanged' regular
 * structure
 */
public data class TriggerNumberOfExecutionsChanged(
    public val triggerId: TriggerId,
    public val `by`: Long
) {
    public companion object :
        ScaleReader<TriggerNumberOfExecutionsChanged>,
        ScaleWriter<TriggerNumberOfExecutionsChanged> {
        public override fun read(reader: ScaleCodecReader): TriggerNumberOfExecutionsChanged = try {
            TriggerNumberOfExecutionsChanged(
                TriggerId.read(reader),
                reader.readUint32(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: TriggerNumberOfExecutionsChanged) =
            try {
                TriggerId.write(writer, instance.triggerId)
                writer.writeUint32(instance.`by`)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
