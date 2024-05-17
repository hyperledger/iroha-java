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
 * EventEventFilterBox
 *
 * Generated from 'EventEventFilterBox' enum
 */
public sealed class EventEventFilterBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Pipeline' variant
     */
    public data class Pipeline(
        public val pipelineEventEventFilterBox: PipelineEventEventFilterBox,
    ) : EventEventFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.EventEventFilterBox.Pipeline>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.EventEventFilterBox.Pipeline> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.EventEventFilterBox.Pipeline = try {
                Pipeline(
                    PipelineEventEventFilterBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.EventEventFilterBox.Pipeline,
            ): Unit = try {
                PipelineEventEventFilterBox.write(writer, instance.pipelineEventEventFilterBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Data' variant
     */
    public data class Data(
        public val dataEventFilter: DataEventFilter,
    ) : EventEventFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.EventEventFilterBox.Data>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.EventEventFilterBox.Data> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.EventEventFilterBox.Data = try {
                Data(
                    DataEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.EventEventFilterBox.Data,
            ): Unit = try {
                DataEventFilter.write(writer, instance.dataEventFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Time' variant
     */
    public data class Time(
        public val timeEventFilter: TimeEventFilter,
    ) : EventEventFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.EventEventFilterBox.Time>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.EventEventFilterBox.Time> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.EventEventFilterBox.Time = try {
                Time(
                    TimeEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.EventEventFilterBox.Time,
            ): Unit = try {
                TimeEventFilter.write(writer, instance.timeEventFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ExecuteTrigger' variant
     */
    public data class ExecuteTrigger(
        public val executeTriggerEventFilter: ExecuteTriggerEventFilter,
    ) : EventEventFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.EventEventFilterBox.ExecuteTrigger>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.EventEventFilterBox.ExecuteTrigger> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.EventEventFilterBox.ExecuteTrigger = try {
                ExecuteTrigger(
                    ExecuteTriggerEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.EventEventFilterBox.ExecuteTrigger,
            ): Unit = try {
                ExecuteTriggerEventFilter.write(writer, instance.executeTriggerEventFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'TriggerCompleted' variant
     */
    public data class TriggerCompleted(
        public val triggerCompletedEventFilter: TriggerCompletedEventFilter,
    ) : EventEventFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.EventEventFilterBox.TriggerCompleted>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.EventEventFilterBox.TriggerCompleted> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.EventEventFilterBox.TriggerCompleted = try {
                TriggerCompleted(
                    TriggerCompletedEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.EventEventFilterBox.TriggerCompleted,
            ): Unit = try {
                TriggerCompletedEventFilter.write(writer, instance.triggerCompletedEventFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<EventEventFilterBox>, ScaleWriter<EventEventFilterBox> {
        override fun read(reader: ScaleCodecReader): EventEventFilterBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Pipeline.read(reader)
            1 -> Data.read(reader)
            2 -> Time.read(reader)
            3 -> ExecuteTrigger.read(reader)
            4 -> TriggerCompleted.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: EventEventFilterBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Pipeline.write(writer, instance as Pipeline)
                1 -> Data.write(writer, instance as Data)
                2 -> Time.write(writer, instance as Time)
                3 -> ExecuteTrigger.write(writer, instance as ExecuteTrigger)
                4 -> TriggerCompleted.write(writer, instance as TriggerCompleted)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
