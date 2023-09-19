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
 * AssetEventFilter
 *
 * Generated from 'AssetEventFilter' enum
 */
public sealed class AssetEventFilter : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    override fun equals(other: Any?): Boolean = when (this) {
        is ByCreated -> ByCreated.equals(this, other)
        is ByDeleted -> ByDeleted.equals(this, other)
        is ByAdded -> ByAdded.equals(this, other)
        is ByRemoved -> ByRemoved.equals(this, other)
        is ByMetadataInserted -> ByMetadataInserted.equals(this, other)
        is ByMetadataRemoved -> ByMetadataRemoved.equals(this, other)
        else -> super.equals(other) }

    override fun hashCode(): Int = when (this) {
        is ByCreated -> ByCreated.hashCode()
        is ByDeleted -> ByDeleted.hashCode()
        is ByAdded -> ByAdded.hashCode()
        is ByRemoved -> ByRemoved.hashCode()
        is ByMetadataInserted -> ByMetadataInserted.hashCode()
        is ByMetadataRemoved -> ByMetadataRemoved.hashCode()
        else -> super.hashCode() }

    /**
     * 'ByCreated' variant
     */
    public class ByCreated : AssetEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByCreated>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByCreated> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByCreated = try {
                ByCreated()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByCreated,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByCreated, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetEventFilter.ByCreated".hashCode()
        }
    }

    /**
     * 'ByDeleted' variant
     */
    public class ByDeleted : AssetEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByDeleted>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByDeleted> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByDeleted = try {
                ByDeleted()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByDeleted,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByDeleted, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetEventFilter.ByDeleted".hashCode()
        }
    }

    /**
     * 'ByAdded' variant
     */
    public class ByAdded : AssetEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByAdded>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByAdded> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByAdded = try {
                ByAdded()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByAdded,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByAdded, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetEventFilter.ByAdded".hashCode()
        }
    }

    /**
     * 'ByRemoved' variant
     */
    public class ByRemoved : AssetEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByRemoved>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByRemoved> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByRemoved = try {
                ByRemoved()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByRemoved,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByRemoved, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetEventFilter.ByRemoved".hashCode()
        }
    }

    /**
     * 'ByMetadataInserted' variant
     */
    public class ByMetadataInserted : AssetEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByMetadataInserted>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByMetadataInserted> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByMetadataInserted = try {
                ByMetadataInserted()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByMetadataInserted,
            ): Unit =
                try {
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByMetadataInserted,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetEventFilter.ByMetadataInserted".hashCode()
        }
    }

    /**
     * 'ByMetadataRemoved' variant
     */
    public class ByMetadataRemoved : AssetEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByMetadataRemoved>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByMetadataRemoved> {
            public const val DISCRIMINANT: Int = 5

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByMetadataRemoved = try {
                ByMetadataRemoved()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByMetadataRemoved,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AssetEventFilter.ByMetadataRemoved,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetEventFilter.ByMetadataRemoved".hashCode()
        }
    }

    public companion object : ScaleReader<AssetEventFilter>, ScaleWriter<AssetEventFilter> {
        override fun read(reader: ScaleCodecReader): AssetEventFilter = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> ByCreated.read(reader)
            1 -> ByDeleted.read(reader)
            2 -> ByAdded.read(reader)
            3 -> ByRemoved.read(reader)
            4 -> ByMetadataInserted.read(reader)
            5 -> ByMetadataRemoved.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: AssetEventFilter) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> ByCreated.write(writer, instance as ByCreated)
                1 -> ByDeleted.write(writer, instance as ByDeleted)
                2 -> ByAdded.write(writer, instance as ByAdded)
                3 -> ByRemoved.write(writer, instance as ByRemoved)
                4 -> ByMetadataInserted.write(writer, instance as ByMetadataInserted)
                5 -> ByMetadataRemoved.write(writer, instance as ByMetadataRemoved)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
