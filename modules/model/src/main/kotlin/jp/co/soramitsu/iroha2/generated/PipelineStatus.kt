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

    public override fun equals(other: Any?) = when (this) {
        is Validating -> Validating.equals(this, other)
        is Committed -> Committed.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is Validating -> Validating.hashCode()
        is Committed -> Committed.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'Validating' variant
     */
    public class Validating : PipelineStatus() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Validating>, ScaleWriter<Validating> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Validating = try {
                Validating()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Validating) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Validating, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".PipelineStatus.Validating".hashCode()
        }
    }

    /**
     * 'Rejected' variant
     */
    public data class Rejected(
        public val pipelineRejectionReason: PipelineRejectionReason
    ) : PipelineStatus() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Rejected>, ScaleWriter<Rejected> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Rejected = try {
                Rejected(
                    PipelineRejectionReason.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Rejected) = try {
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
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Committed>, ScaleWriter<Committed> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Committed = try {
                Committed()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Committed) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Committed, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".PipelineStatus.Committed".hashCode()
        }
    }

    public companion object : ScaleReader<PipelineStatus>, ScaleWriter<PipelineStatus> {
        public override fun read(reader: ScaleCodecReader): PipelineStatus = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Validating.read(reader)
            1 -> Rejected.read(reader)
            2 -> Committed.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: PipelineStatus) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Validating.write(writer, instance as Validating)
                1 -> Rejected.write(writer, instance as Rejected)
                2 -> Committed.write(writer, instance as Committed)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
