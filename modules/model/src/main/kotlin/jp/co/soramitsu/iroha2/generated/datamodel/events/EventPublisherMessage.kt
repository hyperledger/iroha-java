//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * EventPublisherMessage
 *
 * Generated from 'iroha_data_model::events::EventPublisherMessage' enum
 */
public sealed class EventPublisherMessage : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'SubscriptionAccepted' variant
     */
    public class SubscriptionAccepted : EventPublisherMessage() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<SubscriptionAccepted>, ScaleWriter<SubscriptionAccepted> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): SubscriptionAccepted = try {
                SubscriptionAccepted()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: SubscriptionAccepted) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Event' variant
     */
    public data class Event(
        public val event: jp.co.soramitsu.iroha2.generated.datamodel.events.Event
    ) : EventPublisherMessage() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Event>, ScaleWriter<Event> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Event = try {
                Event(
                    jp.co.soramitsu.iroha2.generated.datamodel.events.Event.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Event) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.events.Event.write(writer, instance.event)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<EventPublisherMessage>, ScaleWriter<EventPublisherMessage> {
        public override fun read(reader: ScaleCodecReader): EventPublisherMessage = when (
            val
            discriminant = reader.readUByte()
        ) {
            0 -> SubscriptionAccepted.read(reader)
            1 -> Event.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: EventPublisherMessage) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> SubscriptionAccepted.write(writer, instance as SubscriptionAccepted)
                1 -> Event.write(writer, instance as Event)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
