//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.account

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.FilterOptAssetFilter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * AccountEventFilter
 *
 * Generated from 'iroha_data_model::events::data::events::account::AccountEventFilter' enum
 */
public sealed class AccountEventFilter : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'ByCreated' variant
     */
    public class ByCreated : AccountEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByCreated>, ScaleWriter<ByCreated> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): ByCreated = try {
                ByCreated()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByCreated) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByDeleted' variant
     */
    public class ByDeleted : AccountEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByDeleted>, ScaleWriter<ByDeleted> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): ByDeleted = try {
                ByDeleted()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByDeleted) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByAuthenticationAdded' variant
     */
    public class ByAuthenticationAdded : AccountEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByAuthenticationAdded>, ScaleWriter<ByAuthenticationAdded> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): ByAuthenticationAdded = try {
                ByAuthenticationAdded()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByAuthenticationAdded) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByAuthenticationRemoved' variant
     */
    public class ByAuthenticationRemoved : AccountEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<ByAuthenticationRemoved>,
            ScaleWriter<ByAuthenticationRemoved> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): ByAuthenticationRemoved = try {
                ByAuthenticationRemoved()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByAuthenticationRemoved) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByPermissionAdded' variant
     */
    public class ByPermissionAdded : AccountEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByPermissionAdded>, ScaleWriter<ByPermissionAdded> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): ByPermissionAdded = try {
                ByPermissionAdded()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByPermissionAdded) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByPermissionRemoved' variant
     */
    public class ByPermissionRemoved : AccountEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByPermissionRemoved>, ScaleWriter<ByPermissionRemoved> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): ByPermissionRemoved = try {
                ByPermissionRemoved()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByPermissionRemoved) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByRoleRevoked' variant
     */
    public class ByRoleRevoked : AccountEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByRoleRevoked>, ScaleWriter<ByRoleRevoked> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): ByRoleRevoked = try {
                ByRoleRevoked()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByRoleRevoked) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByRoleGranted' variant
     */
    public class ByRoleGranted : AccountEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByRoleGranted>, ScaleWriter<ByRoleGranted> {
            public const val DISCRIMINANT: Int = 7

            public override fun read(reader: ScaleCodecReader): ByRoleGranted = try {
                ByRoleGranted()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByRoleGranted) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByMetadataInserted' variant
     */
    public class ByMetadataInserted : AccountEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByMetadataInserted>, ScaleWriter<ByMetadataInserted> {
            public const val DISCRIMINANT: Int = 8

            public override fun read(reader: ScaleCodecReader): ByMetadataInserted = try {
                ByMetadataInserted()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByMetadataInserted) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByMetadataRemoved' variant
     */
    public class ByMetadataRemoved : AccountEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByMetadataRemoved>, ScaleWriter<ByMetadataRemoved> {
            public const val DISCRIMINANT: Int = 9

            public override fun read(reader: ScaleCodecReader): ByMetadataRemoved = try {
                ByMetadataRemoved()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByMetadataRemoved) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByAsset' variant
     */
    public data class ByAsset(
        public val filterOptAssetFilter: FilterOptAssetFilter
    ) : AccountEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByAsset>, ScaleWriter<ByAsset> {
            public const val DISCRIMINANT: Int = 10

            public override fun read(reader: ScaleCodecReader): ByAsset = try {
                ByAsset(
                    FilterOptAssetFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByAsset) = try {
                FilterOptAssetFilter.write(writer, instance.filterOptAssetFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<AccountEventFilter>, ScaleWriter<AccountEventFilter> {
        public override fun read(reader: ScaleCodecReader): AccountEventFilter = when (
            val discriminant =
                reader.readUByte().toInt()
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
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: AccountEventFilter) {
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
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
