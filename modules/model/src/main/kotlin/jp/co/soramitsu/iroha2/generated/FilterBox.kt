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
 * FilterBox
 *
 * Generated from 'FilterBox' enum
 */
public sealed class FilterBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Pipeline' variant
     */
    public data class Pipeline(
        public val pipelineEventFilter: PipelineEventFilter,
    ) : FilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.FilterBox.Pipeline>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.FilterBox.Pipeline> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.FilterBox.Pipeline = try {
                Pipeline(
                    PipelineEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.FilterBox.Pipeline,
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
    ) : FilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.FilterBox.Data>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.FilterBox.Data> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.FilterBox.Data =
                try {
                    Data(
                        FilterOptOfDataEntityFilter.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.FilterBox.Data,
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
    ) : FilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.FilterBox.Time>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.FilterBox.Time> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.FilterBox.Time =
                try {
                    Time(
                        TimeEventFilter.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.FilterBox.Time,
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
    ) : FilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.FilterBox.ExecuteTrigger>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.FilterBox.ExecuteTrigger> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.FilterBox.ExecuteTrigger = try {
                ExecuteTrigger(
                    ExecuteTriggerEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.FilterBox.ExecuteTrigger,
            ): Unit = try {
                ExecuteTriggerEventFilter.write(writer, instance.executeTriggerEventFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Notification' variant
     */
    public data class Notification(
        public val notificationEventFilter: NotificationEventFilter,
    ) : FilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.FilterBox.Notification>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.FilterBox.Notification> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.FilterBox.Notification = try {
                Notification(
                    NotificationEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.FilterBox.Notification,
            ): Unit = try {
                NotificationEventFilter.write(writer, instance.notificationEventFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<FilterBox>, ScaleWriter<FilterBox> {
        override fun read(reader: ScaleCodecReader): FilterBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Pipeline.read(reader)
            1 -> Data.read(reader)
            2 -> Time.read(reader)
            3 -> ExecuteTrigger.read(reader)
            4 -> Notification.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: FilterBox) {
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
