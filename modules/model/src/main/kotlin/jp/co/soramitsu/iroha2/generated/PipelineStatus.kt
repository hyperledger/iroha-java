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
import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.Unit

/**
 * PipelineStatus
 *
 * Generated from 'PipelineStatus' enum
 */
public sealed class PipelineStatus : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    override fun equals(other: Any?): Boolean = when (this) {
        is Validating -> Validating.equals(this, other)
        is Committed -> Committed.equals(this, other)
        else -> super.equals(other) }

    override fun hashCode(): Int = when (this) {
        is Validating -> Validating.hashCode()
        is Committed -> Committed.hashCode()
        else -> super.hashCode() }

    /**
     * 'Validating' variant
     */
    public class Validating : PipelineStatus() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.PipelineStatus.Validating>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.PipelineStatus.Validating> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.PipelineStatus.Validating = try {
                Validating()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.PipelineStatus.Validating,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: jp.co.soramitsu.iroha2.generated.PipelineStatus.Validating, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".PipelineStatus.Validating".hashCode()
        }
    }

    /**
     * 'Rejected' variant
     */
    public data class Rejected(
        public val pipelineRejectionReason: PipelineRejectionReason,
    ) : PipelineStatus() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.PipelineStatus.Rejected>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.PipelineStatus.Rejected> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.PipelineStatus.Rejected = try {
                Rejected(
                    PipelineRejectionReason.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.PipelineStatus.Rejected,
            ): Unit = try {
                PipelineRejectionReason.write(writer, instance.pipelineRejectionReason)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Committed' variant
     */
    public class Committed : PipelineStatus() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.PipelineStatus.Committed>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.PipelineStatus.Committed> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.PipelineStatus.Committed = try {
                Committed()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.PipelineStatus.Committed,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: jp.co.soramitsu.iroha2.generated.PipelineStatus.Committed, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".PipelineStatus.Committed".hashCode()
        }
    }

    public companion object : ScaleReader<PipelineStatus>, ScaleWriter<PipelineStatus> {
        override fun read(reader: ScaleCodecReader): PipelineStatus = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Validating.read(reader)
            1 -> Rejected.read(reader)
            2 -> Committed.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: PipelineStatus) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Validating.write(writer, instance as Validating)
                1 -> Rejected.write(writer, instance as Rejected)
                2 -> Committed.write(writer, instance as Committed)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
