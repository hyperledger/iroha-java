//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.trigger

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * Trigger
 *
 * Generated from 'iroha_data_model::trigger::Trigger' regular structure
 */
public data class Trigger(
    public val id: Id,
    public val action: Action
) {
    public companion object : ScaleReader<Trigger>, ScaleWriter<Trigger> {
        public override fun read(reader: ScaleCodecReader): Trigger = try {
            Trigger(
                Id.read(reader),
                Action.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Trigger) = try {
            Id.write(writer, instance.id)
            Action.write(writer, instance.action)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
