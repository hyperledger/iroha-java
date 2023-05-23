//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any

/**
 * Action
 *
 * Generated from 'Action<FilterBox, OptimizedExecutable>' regular structure
 */
public data class Action<T0, T1>(
    public val executable: OptimizedExecutable,
    public val repeats: Repeats,
    public val technicalAccount: AccountId,
    public val filter: FilterBox,
    public val metadata: Metadata
) {
    public companion object :
        ScaleReader<Action<out Any, out Any>>,
        ScaleWriter<Action<out Any, out
                Any>> {
        public override fun read(reader: ScaleCodecReader): Action<out Any, out Any> = try {
            Action(
                OptimizedExecutable.read(reader),
                Repeats.read(reader),
                AccountId.read(reader),
                FilterBox.read(reader),
                Metadata.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Action<out Any, out Any>) = try {
            OptimizedExecutable.write(writer, instance.executable)
            Repeats.write(writer, instance.repeats)
            AccountId.write(writer, instance.technicalAccount)
            FilterBox.write(writer, instance.filter)
            Metadata.write(writer, instance.metadata)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
