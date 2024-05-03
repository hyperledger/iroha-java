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
 * MetadataError
 *
 * Generated from 'MetadataError' enum
 */
public sealed class MetadataError : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    override fun equals(other: Any?): Boolean = when (this) {
        is EmptyPath -> EmptyPath.equals(this, other)
        else -> super.equals(other) }

    override fun hashCode(): Int = when (this) {
        is EmptyPath -> EmptyPath.hashCode()
        else -> super.hashCode() }

    /**
     * 'EmptyPath' variant
     */
    public class EmptyPath : MetadataError() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.MetadataError.EmptyPath>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.MetadataError.EmptyPath> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.MetadataError.EmptyPath = try {
                EmptyPath()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.MetadataError.EmptyPath,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: jp.co.soramitsu.iroha2.generated.MetadataError.EmptyPath, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".MetadataError.EmptyPath".hashCode()
        }
    }

    /**
     * 'EntryTooBig' variant
     */
    public data class EntryTooBig(
        public val sizeError: SizeError,
    ) : MetadataError() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.MetadataError.EntryTooBig>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.MetadataError.EntryTooBig> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.MetadataError.EntryTooBig = try {
                EntryTooBig(
                    SizeError.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.MetadataError.EntryTooBig,
            ): Unit = try {
                SizeError.write(writer, instance.sizeError)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'MaxCapacity' variant
     */
    public data class MaxCapacity(
        public val sizeError: SizeError,
    ) : MetadataError() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.MetadataError.MaxCapacity>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.MetadataError.MaxCapacity> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.MetadataError.MaxCapacity = try {
                MaxCapacity(
                    SizeError.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.MetadataError.MaxCapacity,
            ): Unit = try {
                SizeError.write(writer, instance.sizeError)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'MissingSegment' variant
     */
    public data class MissingSegment(
        public val name: Name,
    ) : MetadataError() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.MetadataError.MissingSegment>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.MetadataError.MissingSegment> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.MetadataError.MissingSegment = try {
                MissingSegment(
                    Name.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.MetadataError.MissingSegment,
            ): Unit = try {
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
        public val name: Name,
    ) : MetadataError() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.MetadataError.InvalidSegment>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.MetadataError.InvalidSegment> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.MetadataError.InvalidSegment = try {
                InvalidSegment(
                    Name.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.MetadataError.InvalidSegment,
            ): Unit = try {
                Name.write(writer, instance.name)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<MetadataError>, ScaleWriter<MetadataError> {
        override fun read(reader: ScaleCodecReader): MetadataError = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> EmptyPath.read(reader)
            1 -> EntryTooBig.read(reader)
            2 -> MaxCapacity.read(reader)
            3 -> MissingSegment.read(reader)
            4 -> InvalidSegment.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: MetadataError) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> EmptyPath.write(writer, instance as EmptyPath)
                1 -> EntryTooBig.write(writer, instance as EntryTooBig)
                2 -> MaxCapacity.write(writer, instance as MaxCapacity)
                3 -> MissingSegment.write(writer, instance as MissingSegment)
                4 -> InvalidSegment.write(writer, instance as InvalidSegment)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
