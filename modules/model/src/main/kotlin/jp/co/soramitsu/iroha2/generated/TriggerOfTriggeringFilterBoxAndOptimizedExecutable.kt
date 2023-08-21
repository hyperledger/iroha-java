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
 * TriggerOfTriggeringFilterBoxAndOptimizedExecutable
 *
 * Generated from 'TriggerOfTriggeringFilterBoxAndOptimizedExecutable' regular structure
 */
public data class TriggerOfTriggeringFilterBoxAndOptimizedExecutable(
    public val id: TriggerId,
    public val action: ActionOfTriggeringFilterBoxAndOptimizedExecutable,
) {
    public companion object :
        ScaleReader<TriggerOfTriggeringFilterBoxAndOptimizedExecutable>,
        ScaleWriter<TriggerOfTriggeringFilterBoxAndOptimizedExecutable> {
        override fun read(reader: ScaleCodecReader): TriggerOfTriggeringFilterBoxAndOptimizedExecutable = try {
            TriggerOfTriggeringFilterBoxAndOptimizedExecutable(
                TriggerId.read(reader),
                ActionOfTriggeringFilterBoxAndOptimizedExecutable.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(
            writer: ScaleCodecWriter,
            instance: TriggerOfTriggeringFilterBoxAndOptimizedExecutable,
        ): Unit = try {
            TriggerId.write(writer, instance.id)
            ActionOfTriggeringFilterBoxAndOptimizedExecutable.write(writer, instance.action)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
