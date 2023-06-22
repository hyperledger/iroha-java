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
        public val filterOptOfPeerFilter: FilterOptOfPeerFilter
    ) : DataEntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByPeer>, ScaleWriter<ByPeer> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): ByPeer = try {
                ByPeer(
                    FilterOptOfPeerFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByPeer) = try {
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
        public val filterOptOfDomainFilter: FilterOptOfDomainFilter
    ) : DataEntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByDomain>, ScaleWriter<ByDomain> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): ByDomain = try {
                ByDomain(
                    FilterOptOfDomainFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByDomain) = try {
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
        public val filterOptOfAccountFilter: FilterOptOfAccountFilter
    ) : DataEntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByAccount>, ScaleWriter<ByAccount> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): ByAccount = try {
                ByAccount(
                    FilterOptOfAccountFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByAccount) = try {
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
        public val filterOptOfAssetDefinitionFilter: FilterOptOfAssetDefinitionFilter
    ) : DataEntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByAssetDefinition>, ScaleWriter<ByAssetDefinition> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): ByAssetDefinition = try {
                ByAssetDefinition(
                    FilterOptOfAssetDefinitionFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByAssetDefinition) = try {
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
        public val filterOptOfAssetFilter: FilterOptOfAssetFilter
    ) : DataEntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByAsset>, ScaleWriter<ByAsset> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): ByAsset = try {
                ByAsset(
                    FilterOptOfAssetFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByAsset) = try {
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
        public val filterOptOfTriggerFilter: FilterOptOfTriggerFilter
    ) : DataEntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByTrigger>, ScaleWriter<ByTrigger> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): ByTrigger = try {
                ByTrigger(
                    FilterOptOfTriggerFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByTrigger) = try {
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
        public val filterOptOfRoleFilter: FilterOptOfRoleFilter
    ) : DataEntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByRole>, ScaleWriter<ByRole> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): ByRole = try {
                ByRole(
                    FilterOptOfRoleFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByRole) = try {
                FilterOptOfRoleFilter.write(writer, instance.filterOptOfRoleFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<DataEntityFilter>, ScaleWriter<DataEntityFilter> {
        public override fun read(reader: ScaleCodecReader): DataEntityFilter = when (
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
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: DataEntityFilter) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> ByPeer.write(writer, instance as ByPeer)
                1 -> ByDomain.write(writer, instance as ByDomain)
                2 -> ByAccount.write(writer, instance as ByAccount)
                3 -> ByAssetDefinition.write(writer, instance as ByAssetDefinition)
                4 -> ByAsset.write(writer, instance as ByAsset)
                5 -> ByTrigger.write(writer, instance as ByTrigger)
                6 -> ByRole.write(writer, instance as ByRole)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
