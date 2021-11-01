//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * EventSocketMessage
 *
 * Generated from 'iroha_data_model::events::EventSocketMessage' enum
 */
public sealed class EventSocketMessage : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'SubscriptionRequest' variant
     */
    public data class SubscriptionRequest(
        public val subscriptionRequest:  
            jp.co.soramitsu.iroha2.generated.datamodel.events.SubscriptionRequest
    ) : EventSocketMessage() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<SubscriptionRequest>, ScaleWriter<SubscriptionRequest> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): SubscriptionRequest = try {
                SubscriptionRequest(
                    jp.co.soramitsu.iroha2.generated.datamodel.events.SubscriptionRequest.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: SubscriptionRequest) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.events.SubscriptionRequest.write(
                    writer,
                    instance.subscriptionRequest
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'SubscriptionAccepted' variant
     */
    public class SubscriptionAccepted : EventSocketMessage() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<SubscriptionAccepted>, ScaleWriter<SubscriptionAccepted> {
            public const val DISCRIMINANT: Int = 1

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
    ) : EventSocketMessage() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Event>, ScaleWriter<Event> {
            public const val DISCRIMINANT: Int = 2

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

    /**
     * 'EventReceived' variant
     */
    public class EventReceived : EventSocketMessage() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<EventReceived>, ScaleWriter<EventReceived> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): EventReceived = try {
                EventReceived()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: EventReceived) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<EventSocketMessage>, ScaleWriter<EventSocketMessage> {
        public override fun read(reader: ScaleCodecReader): EventSocketMessage = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> SubscriptionRequest.read(reader)
            1 -> SubscriptionAccepted.read(reader)
            2 -> Event.read(reader)
            3 -> EventReceived.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: EventSocketMessage) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> SubscriptionRequest.write(writer, instance as SubscriptionRequest)
                1 -> SubscriptionAccepted.write(writer, instance as SubscriptionAccepted)
                2 -> Event.write(writer, instance as Event)
                3 -> EventReceived.write(writer, instance as EventReceived)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
