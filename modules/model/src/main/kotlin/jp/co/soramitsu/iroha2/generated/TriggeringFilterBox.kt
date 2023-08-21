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
 * TriggeringFilterBox
 *
 * Generated from 'TriggeringFilterBox' enum
 */
public sealed class TriggeringFilterBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Pipeline' variant
     */
    public data class Pipeline(
        public val pipelineEventFilter: PipelineEventFilter,
    ) : TriggeringFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TriggeringFilterBox.Pipeline>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TriggeringFilterBox.Pipeline> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TriggeringFilterBox.Pipeline = try {
                Pipeline(
                    PipelineEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TriggeringFilterBox.Pipeline,
            ): Unit = try {
                PipelineEventFilter.write(writer, instance.pipelineEventFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Data' variant
     */
    public data class Data(
        public val filterOptOfDataEntityFilter: FilterOptOfDataEntityFilter,
    ) : TriggeringFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TriggeringFilterBox.Data>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TriggeringFilterBox.Data> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TriggeringFilterBox.Data = try {
                Data(
                    FilterOptOfDataEntityFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TriggeringFilterBox.Data,
            ): Unit = try {
                FilterOptOfDataEntityFilter.write(writer, instance.filterOptOfDataEntityFilter)
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
    ) : TriggeringFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TriggeringFilterBox.Time>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TriggeringFilterBox.Time> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TriggeringFilterBox.Time = try {
                Time(
                    TimeEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TriggeringFilterBox.Time,
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
    ) : TriggeringFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TriggeringFilterBox.ExecuteTrigger>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TriggeringFilterBox.ExecuteTrigger> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TriggeringFilterBox.ExecuteTrigger = try {
                ExecuteTrigger(
                    ExecuteTriggerEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TriggeringFilterBox.ExecuteTrigger,
            ): Unit = try {
                ExecuteTriggerEventFilter.write(writer, instance.executeTriggerEventFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<TriggeringFilterBox>, ScaleWriter<TriggeringFilterBox> {
        override fun read(reader: ScaleCodecReader): TriggeringFilterBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Pipeline.read(reader)
            1 -> Data.read(reader)
            2 -> Time.read(reader)
            3 -> ExecuteTrigger.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: TriggeringFilterBox) {
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
