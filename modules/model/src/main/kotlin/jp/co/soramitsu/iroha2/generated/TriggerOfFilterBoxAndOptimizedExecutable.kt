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
 * TriggerOfFilterBoxAndOptimizedExecutable
 *
 * Generated from 'TriggerOfFilterBoxAndOptimizedExecutable' regular structure
 */
public data class TriggerOfFilterBoxAndOptimizedExecutable(
    public val id: TriggerId,
    public val action: ActionOfFilterBoxAndOptimizedExecutable,
) {
    public companion object :
        ScaleReader<TriggerOfFilterBoxAndOptimizedExecutable>,
        ScaleWriter<TriggerOfFilterBoxAndOptimizedExecutable> {
        override fun read(reader: ScaleCodecReader): TriggerOfFilterBoxAndOptimizedExecutable = try {
            TriggerOfFilterBoxAndOptimizedExecutable(
                TriggerId.read(reader),
                ActionOfFilterBoxAndOptimizedExecutable.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(
            writer: ScaleCodecWriter,
            instance: TriggerOfFilterBoxAndOptimizedExecutable,
        ): Unit = try {
            TriggerId.write(writer, instance.id)
            ActionOfFilterBoxAndOptimizedExecutable.write(writer, instance.action)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
