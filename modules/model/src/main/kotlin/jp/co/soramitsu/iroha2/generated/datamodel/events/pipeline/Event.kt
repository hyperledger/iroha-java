//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.wrapException

/**
 * Event
 *
 * Generated from 'iroha_data_model::events::pipeline::Event' regular structure
 */
public data class Event(
    public val entityKind: EntityKind,
    public val status: Status,
    public val hash: Hash
) {
    public companion object : ScaleReader<Event>, ScaleWriter<Event> {
        public override fun read(reader: ScaleCodecReader): Event = try {
            Event(
                EntityKind.read(reader),
                Status.read(reader),
                Hash.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Event) = try {
            EntityKind.write(writer, instance.entityKind)
            Status.write(writer, instance.status)
            Hash.write(writer, instance.hash)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
