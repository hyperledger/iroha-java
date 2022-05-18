//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.trigger

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.FilterBox
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.action.Action
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any

/**
 * Trigger
 *
 * Generated from 'iroha_data_model::trigger::Trigger<iroha_data_model::events::FilterBox>' regular
 * structure
 */
public data class Trigger<T0>(
    public val id: Id,
    public val action: Action<FilterBox>
) {
    public companion object : ScaleReader<Trigger<out Any>>, ScaleWriter<Trigger<out Any>> {
        public override fun read(reader: ScaleCodecReader): Trigger<out Any> = try {
            Trigger(
                Id.read(reader),
                Action.read(reader) as Action<FilterBox>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Trigger<out Any>) = try {
            Id.write(writer, instance.id)
            Action.write(writer, instance.action)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
