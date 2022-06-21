//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId
import jp.co.soramitsu.iroha2.wrapException

/**
 * IdFilterTriggerId
 *
 * Generated from 'iroha_data_model::events::data::filters::IdFilterTriggerId' tuple structure
 */
public data class IdFilterTriggerId(
    public val triggerId: TriggerId
) {
    public companion object : ScaleReader<IdFilterTriggerId>, ScaleWriter<IdFilterTriggerId> {
        public override fun read(reader: ScaleCodecReader): IdFilterTriggerId = try {
            IdFilterTriggerId(
                TriggerId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: IdFilterTriggerId) = try {
            TriggerId.write(writer, instance.triggerId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
