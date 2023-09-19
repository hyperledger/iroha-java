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
 * TriggerOfTriggeringFilterBoxAndExecutable
 *
 * Generated from 'TriggerOfTriggeringFilterBoxAndExecutable' regular structure
 */
public data class TriggerOfTriggeringFilterBoxAndExecutable(
    public val id: TriggerId,
    public val action: ActionOfTriggeringFilterBoxAndExecutable,
) {
    public companion object :
        ScaleReader<TriggerOfTriggeringFilterBoxAndExecutable>,
        ScaleWriter<TriggerOfTriggeringFilterBoxAndExecutable> {
        override fun read(reader: ScaleCodecReader): TriggerOfTriggeringFilterBoxAndExecutable = try {
            TriggerOfTriggeringFilterBoxAndExecutable(
                TriggerId.read(reader),
                ActionOfTriggeringFilterBoxAndExecutable.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(
            writer: ScaleCodecWriter,
            instance: TriggerOfTriggeringFilterBoxAndExecutable,
        ): Unit = try {
            TriggerId.write(writer, instance.id)
            ActionOfTriggeringFilterBoxAndExecutable.write(writer, instance.action)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
