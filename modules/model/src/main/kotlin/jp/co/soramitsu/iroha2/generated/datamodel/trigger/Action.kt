//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.trigger

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.events.EventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Executable
import jp.co.soramitsu.iroha2.wrapException

/**
 * Action
 *
 * Generated from 'iroha_data_model::trigger::Action' regular structure
 */
public data class Action(
    public val executable: Executable,
    public val repeats: Repeats,
    public val technicalAccount: Id,
    public val filter: EventFilter
) {
    public companion object : ScaleReader<Action>, ScaleWriter<Action> {
        public override fun read(reader: ScaleCodecReader): Action = try {
            Action(
                Executable.read(reader),
                Repeats.read(reader),
                Id.read(reader),
                EventFilter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Action) = try {
            Executable.write(writer, instance.executable)
            Repeats.write(writer, instance.repeats)
            Id.write(writer, instance.technicalAccount)
            EventFilter.write(writer, instance.filter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
