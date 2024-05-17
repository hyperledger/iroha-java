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
 * TriggeringEventEventFilterBox
 *
 * Generated from 'TriggeringEventEventFilterBox' enum
 */
public sealed class TriggeringEventEventFilterBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Pipeline' variant
     */
    public data class Pipeline(
        public val pipelineEventEventFilterBox: PipelineEventEventFilterBox,
    ) : TriggeringEventEventFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox.Pipeline>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox.Pipeline> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox.Pipeline = try {
                Pipeline(
                    PipelineEventEventFilterBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox.Pipeline,
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
    ) : TriggeringEventEventFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox.Data>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox.Data> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox.Data = try {
                Data(
                    DataEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox.Data,
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
    ) : TriggeringEventEventFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox.Time>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox.Time> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox.Time = try {
                Time(
                    TimeEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox.Time,
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
    ) : TriggeringEventEventFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox.ExecuteTrigger>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox.ExecuteTrigger> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox.ExecuteTrigger = try {
                ExecuteTrigger(
                    ExecuteTriggerEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox.ExecuteTrigger,
            ): Unit = try {
                ExecuteTriggerEventFilter.write(writer, instance.executeTriggerEventFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object :
        ScaleReader<TriggeringEventEventFilterBox>,
        ScaleWriter<TriggeringEventEventFilterBox> {
        override fun read(reader: ScaleCodecReader): TriggeringEventEventFilterBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Pipeline.read(reader)
            1 -> Data.read(reader)
            2 -> Time.read(reader)
            3 -> ExecuteTrigger.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: TriggeringEventEventFilterBox) {
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
