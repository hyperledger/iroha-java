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
 * Trigger
 *
 * Generated from 'Trigger<FilterBox, OptimizedExecutable>' regular structure
 */
public data class Trigger<T0, T1>(
    public val id: TriggerId,
    public val action: Action<FilterBox, OptimizedExecutable>
) {
    public companion object :
        ScaleReader<Trigger<out Any, out Any>>,
        ScaleWriter<Trigger<out Any, out
                Any>> {
        public override fun read(reader: ScaleCodecReader): Trigger<out Any, out Any> = try {
            Trigger(
                TriggerId.read(reader),
                Action.read(reader) as Action<FilterBox, OptimizedExecutable>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Trigger<out Any, out Any>) = try {
            TriggerId.write(writer, instance.id)
            Action.write(writer, instance.action)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
