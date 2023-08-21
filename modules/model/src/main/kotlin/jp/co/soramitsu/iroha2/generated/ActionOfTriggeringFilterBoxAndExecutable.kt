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
 * ActionOfTriggeringFilterBoxAndExecutable
 *
 * Generated from 'ActionOfTriggeringFilterBoxAndExecutable' regular structure
 */
public data class ActionOfTriggeringFilterBoxAndExecutable(
    public val executable: Executable,
    public val repeats: Repeats,
    public val authority: AccountId,
    public val filter: TriggeringFilterBox,
    public val metadata: Metadata,
) {
    public companion object :
        ScaleReader<ActionOfTriggeringFilterBoxAndExecutable>,
        ScaleWriter<ActionOfTriggeringFilterBoxAndExecutable> {
        override fun read(reader: ScaleCodecReader): ActionOfTriggeringFilterBoxAndExecutable = try {
            ActionOfTriggeringFilterBoxAndExecutable(
                Executable.read(reader),
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
            instance: ActionOfTriggeringFilterBoxAndExecutable,
        ): Unit = try {
            Executable.write(writer, instance.executable)
            Repeats.write(writer, instance.repeats)
            AccountId.write(writer, instance.authority)
            TriggeringFilterBox.write(writer, instance.filter)
            Metadata.write(writer, instance.metadata)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
