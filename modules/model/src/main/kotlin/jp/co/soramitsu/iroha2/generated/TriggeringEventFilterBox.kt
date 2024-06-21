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
 * TriggeringEventFilterBox
 *
 * Generated from 'TriggeringEventFilterBox' enum
 */
public sealed class TriggeringEventFilterBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Pipeline' variant
     */
    public data class Pipeline(
        public val pipelineEventFilterBox: PipelineEventFilterBox,
    ) : TriggeringEventFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox.Pipeline>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox.Pipeline> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox.Pipeline = try {
                Pipeline(
                    PipelineEventFilterBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox.Pipeline,
            ): Unit = try {
                PipelineEventFilterBox.write(writer, instance.pipelineEventFilterBox)
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
    ) : TriggeringEventFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox.Data>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox.Data> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox.Data = try {
                Data(
                    DataEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox.Data,
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
    ) : TriggeringEventFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox.Time>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox.Time> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox.Time = try {
                Time(
                    TimeEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox.Time,
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
    ) : TriggeringEventFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox.ExecuteTrigger>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox.ExecuteTrigger> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox.ExecuteTrigger = try {
                ExecuteTrigger(
                    ExecuteTriggerEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox.ExecuteTrigger,
            ): Unit = try {
                ExecuteTriggerEventFilter.write(writer, instance.executeTriggerEventFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object :
        ScaleReader<TriggeringEventFilterBox>,
        ScaleWriter<TriggeringEventFilterBox> {
        override fun read(reader: ScaleCodecReader): TriggeringEventFilterBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Pipeline.read(reader)
            1 -> Data.read(reader)
            2 -> Time.read(reader)
            3 -> ExecuteTrigger.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: TriggeringEventFilterBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Pipeline.write(writer, instance as Pipeline)
                1 -> Data.write(writer, instance as Data)
                2 -> Time.write(writer, instance as Time)
                3 -> ExecuteTrigger.write(writer, instance as ExecuteTrigger)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
