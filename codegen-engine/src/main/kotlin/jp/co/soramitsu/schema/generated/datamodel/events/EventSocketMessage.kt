// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.events

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Int
import kotlin.Unit

/**
 * EventSocketMessage
 *
 * Generated from 'iroha_data_model::events::EventSocketMessage' enum
 */
public sealed class EventSocketMessage {
  /**
   * @return Discriminator of variant in enum
   */
  public abstract fun discriminant(): Int

  /**
   * 'SubscriptionRequest' variant
   */
  public class SubscriptionRequest(
    private val subscriptionRequest:
        jp.co.soramitsu.schema.generated.datamodel.events.SubscriptionRequest
  ) : EventSocketMessage() {
    public override fun discriminant(): Int = 0

    public companion object CODEC : ScaleReader<SubscriptionRequest>,
        ScaleWriter<SubscriptionRequest> {
      public override fun read(reader: ScaleCodecReader): SubscriptionRequest =
          jp.co.soramitsu.schema.generated.datamodel.events.EventSocketMessage.SubscriptionRequest(jp.co.soramitsu.schema.generated.datamodel.events.SubscriptionRequest.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: SubscriptionRequest): Unit {
        jp.co.soramitsu.schema.generated.datamodel.events.SubscriptionRequest.write(writer,
            instance.`subscriptionRequest`)
      }
    }
  }

  /**
   * 'SubscriptionAccepted' variant
   */
  public class SubscriptionAccepted : EventSocketMessage() {
    public override fun discriminant(): Int = 1

    public companion object CODEC : ScaleReader<SubscriptionAccepted>,
        ScaleWriter<SubscriptionAccepted> {
      public override fun read(reader: ScaleCodecReader): SubscriptionAccepted =
          jp.co.soramitsu.schema.generated.datamodel.events.EventSocketMessage.SubscriptionAccepted()

      public override fun write(writer: ScaleCodecWriter, instance: SubscriptionAccepted): Unit {
        //nothing to write, enum variant do not have properties
      }
    }
  }

  /**
   * 'Event' variant
   */
  public class Event(
    private val event: jp.co.soramitsu.schema.generated.datamodel.events.Event
  ) : EventSocketMessage() {
    public override fun discriminant(): Int = 2

    public companion object CODEC : ScaleReader<Event>, ScaleWriter<Event> {
      public override fun read(reader: ScaleCodecReader): Event =
          jp.co.soramitsu.schema.generated.datamodel.events.EventSocketMessage.Event(jp.co.soramitsu.schema.generated.datamodel.events.Event.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Event): Unit {
        jp.co.soramitsu.schema.generated.datamodel.events.Event.write(writer, instance.`event`)
      }
    }
  }

  /**
   * 'EventReceived' variant
   */
  public class EventReceived : EventSocketMessage() {
    public override fun discriminant(): Int = 3

    public companion object CODEC : ScaleReader<EventReceived>, ScaleWriter<EventReceived> {
      public override fun read(reader: ScaleCodecReader): EventReceived =
          jp.co.soramitsu.schema.generated.datamodel.events.EventSocketMessage.EventReceived()

      public override fun write(writer: ScaleCodecWriter, instance: EventReceived): Unit {
        //nothing to write, enum variant do not have properties
      }
    }
  }

  public companion object CODEC : ScaleReader<EventSocketMessage>, ScaleWriter<EventSocketMessage> {
    public override fun read(reader: ScaleCodecReader): EventSocketMessage =
        when(reader.readUByte()) {
    	0 -> SubscriptionRequest.read(reader)
    	1 -> SubscriptionAccepted.read(reader)
    	2 -> Event.read(reader)
    	3 -> EventReceived.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant")
    }

    public override fun write(writer: ScaleCodecWriter, instance: EventSocketMessage): Unit {
      writer.directWrite(instance.discriminant())
      when(instance.discriminant()) {
      	0 -> SubscriptionRequest.write(writer, instance as SubscriptionRequest)
      	1 -> SubscriptionAccepted.write(writer, instance as SubscriptionAccepted)
      	2 -> Event.write(writer, instance as Event)
      	3 -> EventReceived.write(writer, instance as EventReceived)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant")
      }
    }
  }
}
