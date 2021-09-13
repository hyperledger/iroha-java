//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.Hash
import jp.co.soramitsu.iroha2.wrapException

/**
 * Event
 *
 * Generated from 'iroha_data_model::events::pipeline::Event' regular structure
 */
public data class Event(
    public val entityType: EntityType,
    public val status: Status,
    public val hash: Hash
) {
    public companion object : ScaleReader<Event>, ScaleWriter<Event> {
        public override fun read(reader: ScaleCodecReader): Event = try {
            Event(
                EntityType.read(reader),
                Status.read(reader),
                Hash.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Event) = try {
            EntityType.write(writer, instance.entityType)
            Status.write(writer, instance.status)
            Hash.write(writer, instance.hash)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
