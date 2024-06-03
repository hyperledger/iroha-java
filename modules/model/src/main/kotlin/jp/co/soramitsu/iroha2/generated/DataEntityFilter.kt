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
 * DataEntityFilter
 *
 * Generated from 'DataEntityFilter' enum
 */
public sealed class DataEntityFilter : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'ByPeer' variant
     */
    public data class ByPeer(
        public val filterOptOfPeerFilter: FilterOptOfPeerFilter,
    ) : DataEntityFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByPeer>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByPeer> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByPeer = try {
                ByPeer(
                    FilterOptOfPeerFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByPeer,
            ): Unit = try {
                FilterOptOfPeerFilter.write(writer, instance.filterOptOfPeerFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByDomain' variant
     */
    public data class ByDomain(
        public val filterOptOfDomainFilter: FilterOptOfDomainFilter,
    ) : DataEntityFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByDomain>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByDomain> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByDomain = try {
                ByDomain(
                    FilterOptOfDomainFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByDomain,
            ): Unit = try {
                FilterOptOfDomainFilter.write(writer, instance.filterOptOfDomainFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByAccount' variant
     */
    public data class ByAccount(
        public val filterOptOfAccountFilter: FilterOptOfAccountFilter,
    ) : DataEntityFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByAccount>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByAccount> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByAccount = try {
                ByAccount(
                    FilterOptOfAccountFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByAccount,
            ): Unit = try {
                FilterOptOfAccountFilter.write(writer, instance.filterOptOfAccountFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByAssetDefinition' variant
     */
    public data class ByAssetDefinition(
        public val filterOptOfAssetDefinitionFilter: FilterOptOfAssetDefinitionFilter,
    ) : DataEntityFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByAssetDefinition>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByAssetDefinition> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByAssetDefinition = try {
                ByAssetDefinition(
                    FilterOptOfAssetDefinitionFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByAssetDefinition,
            ): Unit = try {
                FilterOptOfAssetDefinitionFilter.write(writer, instance.filterOptOfAssetDefinitionFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByAsset' variant
     */
    public data class ByAsset(
        public val filterOptOfAssetFilter: FilterOptOfAssetFilter,
    ) : DataEntityFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByAsset>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByAsset> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByAsset = try {
                ByAsset(
                    FilterOptOfAssetFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByAsset,
            ): Unit = try {
                FilterOptOfAssetFilter.write(writer, instance.filterOptOfAssetFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByTrigger' variant
     */
    public data class ByTrigger(
        public val filterOptOfTriggerFilter: FilterOptOfTriggerFilter,
    ) : DataEntityFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByTrigger>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByTrigger> {
            public const val DISCRIMINANT: Int = 5

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByTrigger = try {
                ByTrigger(
                    FilterOptOfTriggerFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByTrigger,
            ): Unit = try {
                FilterOptOfTriggerFilter.write(writer, instance.filterOptOfTriggerFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByRole' variant
     */
    public data class ByRole(
        public val filterOptOfRoleFilter: FilterOptOfRoleFilter,
    ) : DataEntityFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByRole>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByRole> {
            public const val DISCRIMINANT: Int = 6

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByRole = try {
                ByRole(
                    FilterOptOfRoleFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.DataEntityFilter.ByRole,
            ): Unit = try {
                FilterOptOfRoleFilter.write(writer, instance.filterOptOfRoleFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<DataEntityFilter>, ScaleWriter<DataEntityFilter> {
        override fun read(reader: ScaleCodecReader): DataEntityFilter = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> ByPeer.read(reader)
            1 -> ByDomain.read(reader)
            2 -> ByAccount.read(reader)
            3 -> ByAssetDefinition.read(reader)
            4 -> ByAsset.read(reader)
            5 -> ByTrigger.read(reader)
            6 -> ByRole.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: DataEntityFilter) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> ByPeer.write(writer, instance as ByPeer)
                1 -> ByDomain.write(writer, instance as ByDomain)
                2 -> ByAccount.write(writer, instance as ByAccount)
                3 -> ByAssetDefinition.write(writer, instance as ByAssetDefinition)
                4 -> ByAsset.write(writer, instance as ByAsset)
                5 -> ByTrigger.write(writer, instance as ByTrigger)
                6 -> ByRole.write(writer, instance as ByRole)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
