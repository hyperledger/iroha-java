//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * EntityFilter
 *
 * Generated from 'iroha_data_model::events::data::filters::EntityFilter' enum
 */
public sealed class EntityFilter : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'ByPeer' variant
     */
    public data class ByPeer(
        public val filterOptPeerFilter: FilterOptPeerFilter
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByPeer>, ScaleWriter<ByPeer> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): ByPeer = try {
                ByPeer(
                    FilterOptPeerFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByPeer) = try {
                FilterOptPeerFilter.write(writer, instance.filterOptPeerFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByDomain' variant
     */
    public data class ByDomain(
        public val filterOptDomainFilter: FilterOptDomainFilter
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByDomain>, ScaleWriter<ByDomain> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): ByDomain = try {
                ByDomain(
                    FilterOptDomainFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByDomain) = try {
                FilterOptDomainFilter.write(writer, instance.filterOptDomainFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByAccount' variant
     */
    public data class ByAccount(
        public val filterOptAccountFilter: FilterOptAccountFilter
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByAccount>, ScaleWriter<ByAccount> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): ByAccount = try {
                ByAccount(
                    FilterOptAccountFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByAccount) = try {
                FilterOptAccountFilter.write(writer, instance.filterOptAccountFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByAssetDefinition' variant
     */
    public data class ByAssetDefinition(
        public val filterOptAssetDefinitionFilter: FilterOptAssetDefinitionFilter
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByAssetDefinition>, ScaleWriter<ByAssetDefinition> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): ByAssetDefinition = try {
                ByAssetDefinition(
                    FilterOptAssetDefinitionFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByAssetDefinition) = try {
                FilterOptAssetDefinitionFilter.write(writer, instance.filterOptAssetDefinitionFilter)
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
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByAsset>, ScaleWriter<ByAsset> {
            public const val DISCRIMINANT: Int = 4

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

    /**
     * 'ByTrigger' variant
     */
    public data class ByTrigger(
        public val filterOptTriggerFilter: FilterOptTriggerFilter
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByTrigger>, ScaleWriter<ByTrigger> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): ByTrigger = try {
                ByTrigger(
                    FilterOptTriggerFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByTrigger) = try {
                FilterOptTriggerFilter.write(writer, instance.filterOptTriggerFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByRole' variant
     */
    public data class ByRole(
        public val filterOptRoleFilter: FilterOptRoleFilter
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByRole>, ScaleWriter<ByRole> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): ByRole = try {
                ByRole(
                    FilterOptRoleFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByRole) = try {
                FilterOptRoleFilter.write(writer, instance.filterOptRoleFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<EntityFilter>, ScaleWriter<EntityFilter> {
        public override fun read(reader: ScaleCodecReader): EntityFilter = when (
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

        public override fun write(writer: ScaleCodecWriter, instance: EntityFilter) {
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
