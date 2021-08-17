//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import java.util.Optional
import jp.co.soramitsu.iroha2.generated.crypto.Hash
import kotlin.Unit

/**
 * EventFilter
 *
 * Generated from 'iroha_data_model::events::pipeline::EventFilter' regular structure
 */
public data class EventFilter(
  public val entity: EntityType?,
  public val hash: Hash?
) {
  public companion object : ScaleReader<EventFilter>, ScaleWriter<EventFilter> {
    public override fun read(reader: ScaleCodecReader): EventFilter = EventFilter(
      reader.readOptional(EntityType).orElse(null),
      reader.readOptional(Hash).orElse(null),
    )

    public override fun write(writer: ScaleCodecWriter, instance: EventFilter): Unit {
        writer.writeOptional(EntityType, Optional.ofNullable(instance.entity))
        writer.writeOptional(Hash, Optional.ofNullable(instance.hash))
    }
  }
}
