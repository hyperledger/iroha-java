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
 * MetadataError
 *
 * Generated from 'MetadataError' enum
 */
public sealed class MetadataError : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is EmptyPath -> EmptyPath.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is EmptyPath -> EmptyPath.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'EntryTooBig' variant
     */
    public data class EntryTooBig(
        public val sizeError: SizeError
    ) : MetadataError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<EntryTooBig>, ScaleWriter<EntryTooBig> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): EntryTooBig = try {
                EntryTooBig(
                    SizeError.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: EntryTooBig) = try {
                SizeError.write(writer, instance.sizeError)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'OverallSize' variant
     */
    public data class OverallSize(
        public val sizeError: SizeError
    ) : MetadataError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<OverallSize>, ScaleWriter<OverallSize> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): OverallSize = try {
                OverallSize(
                    SizeError.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: OverallSize) = try {
                SizeError.write(writer, instance.sizeError)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'EmptyPath' variant
     */
    public class EmptyPath : MetadataError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<EmptyPath>, ScaleWriter<EmptyPath> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): EmptyPath = try {
                EmptyPath()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: EmptyPath) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: EmptyPath, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".MetadataError.EmptyPath".hashCode()
        }
    }

    /**
     * 'MissingSegment' variant
     */
    public data class MissingSegment(
        public val name: Name
    ) : MetadataError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<MissingSegment>, ScaleWriter<MissingSegment> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): MissingSegment = try {
                MissingSegment(
                    Name.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: MissingSegment) = try {
                Name.write(writer, instance.name)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'InvalidSegment' variant
     */
    public data class InvalidSegment(
        public val name: Name
    ) : MetadataError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<InvalidSegment>, ScaleWriter<InvalidSegment> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): InvalidSegment = try {
                InvalidSegment(
                    Name.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: InvalidSegment) = try {
                Name.write(writer, instance.name)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<MetadataError>, ScaleWriter<MetadataError> {
        public override fun read(reader: ScaleCodecReader): MetadataError = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> EntryTooBig.read(reader)
            1 -> OverallSize.read(reader)
            2 -> EmptyPath.read(reader)
            3 -> MissingSegment.read(reader)
            4 -> InvalidSegment.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: MetadataError) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> EntryTooBig.write(writer, instance as EntryTooBig)
                1 -> OverallSize.write(writer, instance as OverallSize)
                2 -> EmptyPath.write(writer, instance as EmptyPath)
                3 -> MissingSegment.write(writer, instance as MissingSegment)
                4 -> InvalidSegment.write(writer, instance as InvalidSegment)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
