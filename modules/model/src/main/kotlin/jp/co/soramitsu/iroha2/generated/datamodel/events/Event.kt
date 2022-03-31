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
 * Event
 *
 * Generated from 'iroha_data_model::events::Event' enum
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
        public val event: jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.Event
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Pipeline>, ScaleWriter<Pipeline> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Pipeline = try {
                Pipeline(
                    jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.Event.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Pipeline) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.Event.write(
                    writer,
                    instance.event
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Data' variant
     */
    public data class Data(
        public val event: jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.Event
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Data>, ScaleWriter<Data> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Data = try {
                Data(
                    jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.Event.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Data) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.Event.write(
                    writer,
                    instance.event
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
        public val event: jp.co.soramitsu.iroha2.generated.datamodel.events.time.Event
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Time>, ScaleWriter<Time> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Time = try {
                Time(
                    jp.co.soramitsu.iroha2.generated.datamodel.events.time.Event.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Time) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.events.time.Event.write(writer, instance.event)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ExecuteTrigger' variant
     */
    public data class ExecuteTrigger(
        public val event: jp.co.soramitsu.iroha2.generated.datamodel.events.executetrigger.Event
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ExecuteTrigger>, ScaleWriter<ExecuteTrigger> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): ExecuteTrigger = try {
                ExecuteTrigger(
                    jp.co.soramitsu.iroha2.generated.datamodel.events.executetrigger.Event.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ExecuteTrigger) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.events.executetrigger.Event.write(
                    writer,
                    instance.event
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Event>, ScaleWriter<Event> {
        public override fun read(reader: ScaleCodecReader): Event = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> Pipeline.read(reader)
            1 -> Data.read(reader)
            2 -> Time.read(reader)
            3 -> ExecuteTrigger.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Event) {
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
