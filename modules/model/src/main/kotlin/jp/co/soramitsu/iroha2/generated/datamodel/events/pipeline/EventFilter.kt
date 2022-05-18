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
 * EventFilter
 *
 * Generated from 'iroha_data_model::events::pipeline::EventFilter' regular structure
 */
public data class EventFilter(
    public val entityKind: EntityKind?,
    public val statusKind: StatusKind?,
    public val hash: Hash?
) {
    public companion object : ScaleReader<EventFilter>, ScaleWriter<EventFilter> {
        public override fun read(reader: ScaleCodecReader): EventFilter = try {
            EventFilter(
                reader.readNullable(EntityKind),
                reader.readNullable(StatusKind),
                reader.readNullable(Hash),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: EventFilter) = try {
            writer.writeNullable(EntityKind, instance.entityKind)
            writer.writeNullable(StatusKind, instance.statusKind)
            writer.writeNullable(Hash, instance.hash)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
