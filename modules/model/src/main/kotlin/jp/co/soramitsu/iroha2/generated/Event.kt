//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int
import kotlin.Unit

/**
 * Event
 *
 * Generated from 'Event' enum
 */
public sealed class Event : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Pipeline' variant
     */
    public data class Pipeline(
        public val pipelineEvent: PipelineEvent,
    ) : Event() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Event.Pipeline>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Event.Pipeline> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Event.Pipeline =
                try {
                    Pipeline(
                        PipelineEvent.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Event.Pipeline,
            ): Unit = try {
                PipelineEvent.write(writer, instance.pipelineEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Data' variant
     */
    public data class Data(
        public val dataEvent: DataEvent,
    ) : Event() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Event.Data>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Event.Data> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Event.Data = try {
                Data(
                    DataEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Event.Data,
            ): Unit = try {
                DataEvent.write(writer, instance.dataEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Time' variant
     */
    public data class Time(
        public val timeEvent: TimeEvent,
    ) : Event() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Event.Time>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Event.Time> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Event.Time = try {
                Time(
                    TimeEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Event.Time,
            ): Unit = try {
                TimeEvent.write(writer, instance.timeEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ExecuteTrigger' variant
     */
    public data class ExecuteTrigger(
        public val executeTriggerEvent: ExecuteTriggerEvent,
    ) : Event() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Event.ExecuteTrigger>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Event.ExecuteTrigger> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Event.ExecuteTrigger = try {
                ExecuteTrigger(
                    ExecuteTriggerEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Event.ExecuteTrigger,
            ): Unit = try {
                ExecuteTriggerEvent.write(writer, instance.executeTriggerEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Notification' variant
     */
    public data class Notification(
        public val notificationEvent: NotificationEvent,
    ) : Event() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Event.Notification>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Event.Notification> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Event.Notification = try {
                Notification(
                    NotificationEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Event.Notification,
            ): Unit = try {
                NotificationEvent.write(writer, instance.notificationEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Event>, ScaleWriter<Event> {
        override fun read(reader: ScaleCodecReader): Event = when (val discriminant = reader.readUByte()) {
            0 -> Pipeline.read(reader)
            1 -> Data.read(reader)
            2 -> Time.read(reader)
            3 -> ExecuteTrigger.read(reader)
            4 -> Notification.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: Event) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Pipeline.write(writer, instance as Pipeline)
                1 -> Data.write(writer, instance as Data)
                2 -> Time.write(writer, instance as Time)
                3 -> ExecuteTrigger.write(writer, instance as ExecuteTrigger)
                4 -> Notification.write(writer, instance as Notification)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
