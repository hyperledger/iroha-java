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
 * AccountEventFilter
 *
 * Generated from 'AccountEventFilter' enum
 */
public sealed class AccountEventFilter : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    override fun equals(other: Any?): Boolean = when (this) {
        is ByCreated -> ByCreated.equals(this, other)
        is ByDeleted -> ByDeleted.equals(this, other)
        is ByAuthenticationAdded -> ByAuthenticationAdded.equals(this, other)
        is ByAuthenticationRemoved -> ByAuthenticationRemoved.equals(this, other)
        is ByPermissionAdded -> ByPermissionAdded.equals(this, other)
        is ByPermissionRemoved -> ByPermissionRemoved.equals(this, other)
        is ByRoleRevoked -> ByRoleRevoked.equals(this, other)
        is ByRoleGranted -> ByRoleGranted.equals(this, other)
        is ByMetadataInserted -> ByMetadataInserted.equals(this, other)
        is ByMetadataRemoved -> ByMetadataRemoved.equals(this, other)
        else -> super.equals(other) }

    override fun hashCode(): Int = when (this) {
        is ByCreated -> ByCreated.hashCode()
        is ByDeleted -> ByDeleted.hashCode()
        is ByAuthenticationAdded -> ByAuthenticationAdded.hashCode()
        is ByAuthenticationRemoved -> ByAuthenticationRemoved.hashCode()
        is ByPermissionAdded -> ByPermissionAdded.hashCode()
        is ByPermissionRemoved -> ByPermissionRemoved.hashCode()
        is ByRoleRevoked -> ByRoleRevoked.hashCode()
        is ByRoleGranted -> ByRoleGranted.hashCode()
        is ByMetadataInserted -> ByMetadataInserted.hashCode()
        is ByMetadataRemoved -> ByMetadataRemoved.hashCode()
        else -> super.hashCode() }

    /**
     * 'ByCreated' variant
     */
    public class ByCreated : AccountEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByCreated>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByCreated> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByCreated = try {
                ByCreated()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByCreated,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByCreated,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AccountEventFilter.ByCreated".hashCode()
        }
    }

    /**
     * 'ByDeleted' variant
     */
    public class ByDeleted : AccountEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByDeleted>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByDeleted> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByDeleted = try {
                ByDeleted()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByDeleted,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByDeleted,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AccountEventFilter.ByDeleted".hashCode()
        }
    }

    /**
     * 'ByAuthenticationAdded' variant
     */
    public class ByAuthenticationAdded : AccountEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByAuthenticationAdded>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByAuthenticationAdded> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByAuthenticationAdded = try {
                ByAuthenticationAdded()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByAuthenticationAdded,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByAuthenticationAdded,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AccountEventFilter.ByAuthenticationAdded".hashCode()
        }
    }

    /**
     * 'ByAuthenticationRemoved' variant
     */
    public class ByAuthenticationRemoved : AccountEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByAuthenticationRemoved>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByAuthenticationRemoved> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByAuthenticationRemoved = try {
                ByAuthenticationRemoved()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByAuthenticationRemoved,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByAuthenticationRemoved,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AccountEventFilter.ByAuthenticationRemoved".hashCode()
        }
    }

    /**
     * 'ByPermissionAdded' variant
     */
    public class ByPermissionAdded : AccountEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByPermissionAdded>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByPermissionAdded> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByPermissionAdded = try {
                ByPermissionAdded()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByPermissionAdded,
            ): Unit =
                try {
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByPermissionAdded,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AccountEventFilter.ByPermissionAdded".hashCode()
        }
    }

    /**
     * 'ByPermissionRemoved' variant
     */
    public class ByPermissionRemoved : AccountEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByPermissionRemoved>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByPermissionRemoved> {
            public const val DISCRIMINANT: Int = 5

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByPermissionRemoved = try {
                ByPermissionRemoved()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByPermissionRemoved,
            ): Unit =
                try {
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByPermissionRemoved,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AccountEventFilter.ByPermissionRemoved".hashCode()
        }
    }

    /**
     * 'ByRoleRevoked' variant
     */
    public class ByRoleRevoked : AccountEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByRoleRevoked>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByRoleRevoked> {
            public const val DISCRIMINANT: Int = 6

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByRoleRevoked = try {
                ByRoleRevoked()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByRoleRevoked,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByRoleRevoked,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AccountEventFilter.ByRoleRevoked".hashCode()
        }
    }

    /**
     * 'ByRoleGranted' variant
     */
    public class ByRoleGranted : AccountEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByRoleGranted>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByRoleGranted> {
            public const val DISCRIMINANT: Int = 7

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByRoleGranted = try {
                ByRoleGranted()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByRoleGranted,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByRoleGranted,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AccountEventFilter.ByRoleGranted".hashCode()
        }
    }

    /**
     * 'ByMetadataInserted' variant
     */
    public class ByMetadataInserted : AccountEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByMetadataInserted>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByMetadataInserted> {
            public const val DISCRIMINANT: Int = 8

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByMetadataInserted = try {
                ByMetadataInserted()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByMetadataInserted,
            ): Unit =
                try {
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByMetadataInserted,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AccountEventFilter.ByMetadataInserted".hashCode()
        }
    }

    /**
     * 'ByMetadataRemoved' variant
     */
    public class ByMetadataRemoved : AccountEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByMetadataRemoved>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByMetadataRemoved> {
            public const val DISCRIMINANT: Int = 9

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByMetadataRemoved = try {
                ByMetadataRemoved()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByMetadataRemoved,
            ): Unit =
                try {
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByMetadataRemoved,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AccountEventFilter.ByMetadataRemoved".hashCode()
        }
    }

    /**
     * 'ByAsset' variant
     */
    public data class ByAsset(
        public val filterOptOfAssetFilter: FilterOptOfAssetFilter,
    ) : AccountEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByAsset>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByAsset> {
            public const val DISCRIMINANT: Int = 10

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByAsset = try {
                ByAsset(
                    FilterOptOfAssetFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEventFilter.ByAsset,
            ): Unit = try {
                FilterOptOfAssetFilter.write(writer, instance.filterOptOfAssetFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<AccountEventFilter>, ScaleWriter<AccountEventFilter> {
        override fun read(reader: ScaleCodecReader): AccountEventFilter = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> ByCreated.read(reader)
            1 -> ByDeleted.read(reader)
            2 -> ByAuthenticationAdded.read(reader)
            3 -> ByAuthenticationRemoved.read(reader)
            4 -> ByPermissionAdded.read(reader)
            5 -> ByPermissionRemoved.read(reader)
            6 -> ByRoleRevoked.read(reader)
            7 -> ByRoleGranted.read(reader)
            8 -> ByMetadataInserted.read(reader)
            9 -> ByMetadataRemoved.read(reader)
            10 -> ByAsset.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: AccountEventFilter) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> ByCreated.write(writer, instance as ByCreated)
                1 -> ByDeleted.write(writer, instance as ByDeleted)
                2 -> ByAuthenticationAdded.write(writer, instance as ByAuthenticationAdded)
                3 -> ByAuthenticationRemoved.write(writer, instance as ByAuthenticationRemoved)
                4 -> ByPermissionAdded.write(writer, instance as ByPermissionAdded)
                5 -> ByPermissionRemoved.write(writer, instance as ByPermissionRemoved)
                6 -> ByRoleRevoked.write(writer, instance as ByRoleRevoked)
                7 -> ByRoleGranted.write(writer, instance as ByRoleGranted)
                8 -> ByMetadataInserted.write(writer, instance as ByMetadataInserted)
                9 -> ByMetadataRemoved.write(writer, instance as ByMetadataRemoved)
                10 -> ByAsset.write(writer, instance as ByAsset)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
