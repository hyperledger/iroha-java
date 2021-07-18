//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.Hash
import kotlin.Unit

/**
 * EventFilter
 *
 * Generated from 'iroha_data_model::events::pipeline::EventFilter' regular structure
 */
public class EventFilter(
  public val entity: EntityType?,
  public val hash: Hash?
) {
  public companion object : ScaleReader<EventFilter>, ScaleWriter<EventFilter> {
    public override fun read(reader: ScaleCodecReader): EventFilter =
        EventFilter(jp.co.soramitsu.iroha2.scale.OptionReader(jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.EntityType?).read(reader),
    jp.co.soramitsu.iroha2.scale.OptionReader(jp.co.soramitsu.iroha2.generated.crypto.Hash?).read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: EventFilter): Unit {
      writer.writeOptional(EntityType, instance.entity)
      writer.writeOptional(Hash, instance.hash)
    }
  }
}
