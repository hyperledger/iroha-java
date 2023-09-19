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
 * AssetDefinitionEventFilter
 *
 * Generated from 'AssetDefinitionEventFilter' enum
 */
public sealed class AssetDefinitionEventFilter : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    override fun equals(other: Any?): Boolean = when (this) {
        is ByCreated -> ByCreated.equals(this, other)
        is ByMintabilityChanged -> ByMintabilityChanged.equals(this, other)
        is ByOwnerChanged -> ByOwnerChanged.equals(this, other)
        is ByDeleted -> ByDeleted.equals(this, other)
        is ByMetadataInserted -> ByMetadataInserted.equals(this, other)
        is ByMetadataRemoved -> ByMetadataRemoved.equals(this, other)
        is ByTotalQuantityChanged -> ByTotalQuantityChanged.equals(this, other)
        else -> super.equals(other) }

    override fun hashCode(): Int = when (this) {
        is ByCreated -> ByCreated.hashCode()
        is ByMintabilityChanged -> ByMintabilityChanged.hashCode()
        is ByOwnerChanged -> ByOwnerChanged.hashCode()
        is ByDeleted -> ByDeleted.hashCode()
        is ByMetadataInserted -> ByMetadataInserted.hashCode()
        is ByMetadataRemoved -> ByMetadataRemoved.hashCode()
        is ByTotalQuantityChanged -> ByTotalQuantityChanged.hashCode()
        else -> super.hashCode() }

    /**
     * 'ByCreated' variant
     */
    public class ByCreated : AssetDefinitionEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByCreated>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByCreated> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByCreated = try {
                ByCreated()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByCreated,
            ): Unit =
                try {
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByCreated,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetDefinitionEventFilter.ByCreated".hashCode()
        }
    }

    /**
     * 'ByMintabilityChanged' variant
     */
    public class ByMintabilityChanged : AssetDefinitionEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByMintabilityChanged>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByMintabilityChanged> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByMintabilityChanged = try {
                ByMintabilityChanged()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByMintabilityChanged,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByMintabilityChanged,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetDefinitionEventFilter.ByMintabilityChanged".hashCode()
        }
    }

    /**
     * 'ByOwnerChanged' variant
     */
    public class ByOwnerChanged : AssetDefinitionEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByOwnerChanged>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByOwnerChanged> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByOwnerChanged = try {
                ByOwnerChanged()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByOwnerChanged,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByOwnerChanged,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetDefinitionEventFilter.ByOwnerChanged".hashCode()
        }
    }

    /**
     * 'ByDeleted' variant
     */
    public class ByDeleted : AssetDefinitionEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByDeleted>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByDeleted> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByDeleted = try {
                ByDeleted()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByDeleted,
            ): Unit =
                try {
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByDeleted,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetDefinitionEventFilter.ByDeleted".hashCode()
        }
    }

    /**
     * 'ByMetadataInserted' variant
     */
    public class ByMetadataInserted : AssetDefinitionEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByMetadataInserted>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByMetadataInserted> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByMetadataInserted = try {
                ByMetadataInserted()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByMetadataInserted,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByMetadataInserted,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetDefinitionEventFilter.ByMetadataInserted".hashCode()
        }
    }

    /**
     * 'ByMetadataRemoved' variant
     */
    public class ByMetadataRemoved : AssetDefinitionEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByMetadataRemoved>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByMetadataRemoved> {
            public const val DISCRIMINANT: Int = 5

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByMetadataRemoved = try {
                ByMetadataRemoved()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByMetadataRemoved,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByMetadataRemoved,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetDefinitionEventFilter.ByMetadataRemoved".hashCode()
        }
    }

    /**
     * 'ByTotalQuantityChanged' variant
     */
    public class ByTotalQuantityChanged : AssetDefinitionEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByTotalQuantityChanged>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByTotalQuantityChanged> {
            public const val DISCRIMINANT: Int = 6

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByTotalQuantityChanged = try {
                ByTotalQuantityChanged()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByTotalQuantityChanged,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter.ByTotalQuantityChanged,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetDefinitionEventFilter.ByTotalQuantityChanged".hashCode()
        }
    }

    public companion object :
        ScaleReader<AssetDefinitionEventFilter>,
        ScaleWriter<AssetDefinitionEventFilter> {
        override fun read(reader: ScaleCodecReader): AssetDefinitionEventFilter = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> ByCreated.read(reader)
            1 -> ByMintabilityChanged.read(reader)
            2 -> ByOwnerChanged.read(reader)
            3 -> ByDeleted.read(reader)
            4 -> ByMetadataInserted.read(reader)
            5 -> ByMetadataRemoved.read(reader)
            6 -> ByTotalQuantityChanged.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: AssetDefinitionEventFilter) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> ByCreated.write(writer, instance as ByCreated)
                1 -> ByMintabilityChanged.write(writer, instance as ByMintabilityChanged)
                2 -> ByOwnerChanged.write(writer, instance as ByOwnerChanged)
                3 -> ByDeleted.write(writer, instance as ByDeleted)
                4 -> ByMetadataInserted.write(writer, instance as ByMetadataInserted)
                5 -> ByMetadataRemoved.write(writer, instance as ByMetadataRemoved)
                6 -> ByTotalQuantityChanged.write(writer, instance as ByTotalQuantityChanged)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
