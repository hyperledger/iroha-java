//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * TriggerOfFilterBoxAndExecutable
 *
 * Generated from 'TriggerOfFilterBoxAndExecutable' regular structure
 */
public data class TriggerOfFilterBoxAndExecutable(
    public val id: TriggerId,
    public val action: ActionOfFilterBoxAndExecutable
) {
    public companion object :
        ScaleReader<TriggerOfFilterBoxAndExecutable>,
        ScaleWriter<TriggerOfFilterBoxAndExecutable> {
        public override fun read(reader: ScaleCodecReader): TriggerOfFilterBoxAndExecutable = try {
            TriggerOfFilterBoxAndExecutable(
                TriggerId.read(reader),
                ActionOfFilterBoxAndExecutable.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: TriggerOfFilterBoxAndExecutable) =
            try {
                TriggerId.write(writer, instance.id)
                ActionOfFilterBoxAndExecutable.write(writer, instance.action)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}