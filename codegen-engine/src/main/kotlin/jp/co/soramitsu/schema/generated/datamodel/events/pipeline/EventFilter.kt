// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.events.pipeline

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import java.util.Optional
import jp.co.soramitsu.schema.generated.crypto.Hash
import kotlin.Unit

/**
 * EventFilter
 *
 * Generated from 'iroha_data_model::events::pipeline::EventFilter' regular structure
 */
public class EventFilter(
  private val entity: Optional<EntityType>,
  private val hash: Optional<Hash>
) {
  public companion object CODEC : ScaleReader<EventFilter>, ScaleWriter<EventFilter> {
    public override fun read(reader: ScaleCodecReader): EventFilter =
        EventFilter(reader.readOptional(), reader.readOptional())

    public override fun write(writer: ScaleCodecWriter, instance: EventFilter): Unit {
      writer.writeOptional(instance.entity)
      writer.writeOptional(instance.hash)
    }
  }
}