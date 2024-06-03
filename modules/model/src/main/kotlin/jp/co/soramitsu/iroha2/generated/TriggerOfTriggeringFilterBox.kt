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
 * TriggerOfTriggeringFilterBox
 *
 * Generated from 'TriggerOfTriggeringFilterBox' regular structure
 */
public data class TriggerOfTriggeringFilterBox(
    public val id: TriggerId,
    public val action: ActionOfTriggeringFilterBox,
) {
    public companion object :
        ScaleReader<TriggerOfTriggeringFilterBox>,
        ScaleWriter<TriggerOfTriggeringFilterBox> {
        override fun read(reader: ScaleCodecReader): TriggerOfTriggeringFilterBox = try {
            TriggerOfTriggeringFilterBox(
                TriggerId.read(reader),
                ActionOfTriggeringFilterBox.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: TriggerOfTriggeringFilterBox): Unit = try {
            TriggerId.write(writer, instance.id)
            ActionOfTriggeringFilterBox.write(writer, instance.action)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
