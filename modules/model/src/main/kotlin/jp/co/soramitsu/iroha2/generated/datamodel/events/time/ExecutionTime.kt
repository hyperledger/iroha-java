//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.time

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.Boolean
import kotlin.Int

/**
 * ExecutionTime
 *
 * Generated from 'iroha_data_model::events::time::ExecutionTime' enum
 */
public sealed class ExecutionTime : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is PreCommit -> PreCommit.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is PreCommit -> PreCommit.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'PreCommit' variant
     */
    public class PreCommit : ExecutionTime() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<PreCommit>, ScaleWriter<PreCommit> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): PreCommit = try {
                PreCommit()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: PreCommit) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: PreCommit, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int =
                "datamodel.events.time.ExecutionTime.PreCommit".hashCode()
        }
    }

    /**
     * 'Schedule' variant
     */
    public data class Schedule(
        public val schedule: jp.co.soramitsu.iroha2.generated.datamodel.events.time.Schedule
    ) : ExecutionTime() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Schedule>, ScaleWriter<Schedule> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Schedule = try {
                Schedule(
                    jp.co.soramitsu.iroha2.generated.datamodel.events.time.Schedule.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Schedule) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.events.time.Schedule.write(
                    writer,
                    instance.schedule
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<ExecutionTime>, ScaleWriter<ExecutionTime> {
        public override fun read(reader: ScaleCodecReader): ExecutionTime = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> PreCommit.read(reader)
            1 -> Schedule.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: ExecutionTime) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> PreCommit.write(writer, instance as PreCommit)
                1 -> Schedule.write(writer, instance as Schedule)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
