//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.executetrigger.ExecuteTriggerEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.PipelineEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.time.TimeEvent
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * EventsEvent
 *
 * Generated from 'iroha_data_model::events::EventsEvent' enum
 */
public sealed class EventsEvent : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Pipeline' variant
     */
    public data class Pipeline(
        public val pipelineEvent: PipelineEvent
    ) : EventsEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Pipeline>, ScaleWriter<Pipeline> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Pipeline = try {
                Pipeline(
                    PipelineEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Pipeline) = try {
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
        public val eventsEvent:  
            jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.EventsEvent
    ) : EventsEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Data>, ScaleWriter<Data> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Data = try {
                Data(
                    jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.EventsEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Data) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.EventsEvent.write(
                    writer,
                    instance.eventsEvent
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Time' variant
     */
    public data class Time(
        public val timeEvent: TimeEvent
    ) : EventsEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Time>, ScaleWriter<Time> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Time = try {
                Time(
                    TimeEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Time) = try {
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
        public val executeTriggerEvent: ExecuteTriggerEvent
    ) : EventsEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ExecuteTrigger>, ScaleWriter<ExecuteTrigger> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): ExecuteTrigger = try {
                ExecuteTrigger(
                    ExecuteTriggerEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ExecuteTrigger) = try {
                ExecuteTriggerEvent.write(writer, instance.executeTriggerEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<EventsEvent>, ScaleWriter<EventsEvent> {
        public override fun read(reader: ScaleCodecReader): EventsEvent = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> Pipeline.read(reader)
            1 -> Data.read(reader)
            2 -> Time.read(reader)
            3 -> ExecuteTrigger.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: EventsEvent) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Pipeline.write(writer, instance as Pipeline)
                1 -> Data.write(writer, instance as Data)
                2 -> Time.write(writer, instance as Time)
                3 -> ExecuteTrigger.write(writer, instance as ExecuteTrigger)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
