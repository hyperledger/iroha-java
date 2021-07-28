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
 * Event
 *
 * Generated from 'iroha_data_model::events::pipeline::Event' regular structure
 */
public class Event(
  public val entityType: EntityType,
  public val status: Status,
  public val hash: Hash
) {
  public companion object : ScaleReader<Event>, ScaleWriter<Event> {
    public override fun read(reader: ScaleCodecReader): Event = Event(
      EntityType.read(reader) as EntityType,
      Status.read(reader) as Status,
      Hash.read(reader) as Hash,
    )

    public override fun write(writer: ScaleCodecWriter, instance: Event): Unit {
        EntityType.write(writer, instance.entityType)
        Status.write(writer, instance.status)
        Hash.write(writer, instance.hash)
    }
  }
}
