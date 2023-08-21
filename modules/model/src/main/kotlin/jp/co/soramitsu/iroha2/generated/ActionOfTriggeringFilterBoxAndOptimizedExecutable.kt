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
 * ActionOfTriggeringFilterBoxAndOptimizedExecutable
 *
 * Generated from 'ActionOfTriggeringFilterBoxAndOptimizedExecutable' regular structure
 */
public data class ActionOfTriggeringFilterBoxAndOptimizedExecutable(
    public val executable: OptimizedExecutable,
    public val repeats: Repeats,
    public val authority: AccountId,
    public val filter: TriggeringFilterBox,
    public val metadata: Metadata,
) {
    public companion object :
        ScaleReader<ActionOfTriggeringFilterBoxAndOptimizedExecutable>,
        ScaleWriter<ActionOfTriggeringFilterBoxAndOptimizedExecutable> {
        override fun read(reader: ScaleCodecReader): ActionOfTriggeringFilterBoxAndOptimizedExecutable =
            try {
                ActionOfTriggeringFilterBoxAndOptimizedExecutable(
                    OptimizedExecutable.read(reader),
                    Repeats.read(reader),
                    AccountId.read(reader),
                    TriggeringFilterBox.read(reader),
                    Metadata.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

        override fun write(
            writer: ScaleCodecWriter,
            instance: ActionOfTriggeringFilterBoxAndOptimizedExecutable,
        ): Unit = try {
            OptimizedExecutable.write(writer, instance.executable)
            Repeats.write(writer, instance.repeats)
            AccountId.write(writer, instance.authority)
            TriggeringFilterBox.write(writer, instance.filter)
            Metadata.write(writer, instance.metadata)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
