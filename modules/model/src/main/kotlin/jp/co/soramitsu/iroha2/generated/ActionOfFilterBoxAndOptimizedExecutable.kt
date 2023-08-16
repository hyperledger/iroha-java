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
 * ActionOfFilterBoxAndOptimizedExecutable
 *
 * Generated from 'ActionOfFilterBoxAndOptimizedExecutable' regular structure
 */
public data class ActionOfFilterBoxAndOptimizedExecutable(
    public val executable: OptimizedExecutable,
    public val repeats: Repeats,
    public val authority: AccountId,
    public val filter: FilterBox,
    public val metadata: Metadata,
) {
    public companion object :
        ScaleReader<ActionOfFilterBoxAndOptimizedExecutable>,
        ScaleWriter<ActionOfFilterBoxAndOptimizedExecutable> {
        override fun read(reader: ScaleCodecReader): ActionOfFilterBoxAndOptimizedExecutable = try {
            ActionOfFilterBoxAndOptimizedExecutable(
                OptimizedExecutable.read(reader),
                Repeats.read(reader),
                AccountId.read(reader),
                FilterBox.read(reader),
                Metadata.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: ActionOfFilterBoxAndOptimizedExecutable): Unit = try {
            OptimizedExecutable.write(writer, instance.executable)
            Repeats.write(writer, instance.repeats)
            AccountId.write(writer, instance.authority)
            FilterBox.write(writer, instance.filter)
            Metadata.write(writer, instance.metadata)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
