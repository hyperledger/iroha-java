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
        public val filterOptEventsDataEventsPeerPeerFilter: FilterOptEventsDataEventsPeerPeerFilter
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByPeer>, ScaleWriter<ByPeer> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): ByPeer = try {
                ByPeer(
                    FilterOptEventsDataEventsPeerPeerFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByPeer) = try {
                FilterOptEventsDataEventsPeerPeerFilter.write(
                    writer,
                    instance.filterOptEventsDataEventsPeerPeerFilter
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByDomain' variant
     */
    public data class ByDomain(
        public val filterOptEventsDataEventsDomainDomainFilter:  
            FilterOptEventsDataEventsDomainDomainFilter
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByDomain>, ScaleWriter<ByDomain> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): ByDomain = try {
                ByDomain(
                    FilterOptEventsDataEventsDomainDomainFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByDomain) = try {
                FilterOptEventsDataEventsDomainDomainFilter.write(
                    writer,
                    instance.filterOptEventsDataEventsDomainDomainFilter
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByAccount' variant
     */
    public data class ByAccount(
        public val filterOptEventsDataEventsAccountAccountFilter:  
            FilterOptEventsDataEventsAccountAccountFilter
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByAccount>, ScaleWriter<ByAccount> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): ByAccount = try {
                ByAccount(
                    FilterOptEventsDataEventsAccountAccountFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByAccount) = try {
                FilterOptEventsDataEventsAccountAccountFilter.write(
                    writer,
                    instance.filterOptEventsDataEventsAccountAccountFilter
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByAssetDefinition' variant
     */
    public data class ByAssetDefinition(
        public val filterOptEventsDataEventsAssetAssetDefinitionFilter:  
            FilterOptEventsDataEventsAssetAssetDefinitionFilter
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByAssetDefinition>, ScaleWriter<ByAssetDefinition> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): ByAssetDefinition = try {
                ByAssetDefinition(
                    FilterOptEventsDataEventsAssetAssetDefinitionFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByAssetDefinition) = try {
                FilterOptEventsDataEventsAssetAssetDefinitionFilter.write(
                    writer,
                    instance.filterOptEventsDataEventsAssetAssetDefinitionFilter
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByAsset' variant
     */
    public data class ByAsset(
        public val filterOptEventsDataEventsAssetAssetFilter: FilterOptEventsDataEventsAssetAssetFilter
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByAsset>, ScaleWriter<ByAsset> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): ByAsset = try {
                ByAsset(
                    FilterOptEventsDataEventsAssetAssetFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByAsset) = try {
                FilterOptEventsDataEventsAssetAssetFilter.write(
                    writer,
                    instance.filterOptEventsDataEventsAssetAssetFilter
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByTrigger' variant
     */
    public data class ByTrigger(
        public val filterOptEventsDataEventsTriggerTriggerFilter:  
            FilterOptEventsDataEventsTriggerTriggerFilter
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByTrigger>, ScaleWriter<ByTrigger> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): ByTrigger = try {
                ByTrigger(
                    FilterOptEventsDataEventsTriggerTriggerFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByTrigger) = try {
                FilterOptEventsDataEventsTriggerTriggerFilter.write(
                    writer,
                    instance.filterOptEventsDataEventsTriggerTriggerFilter
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByRole' variant
     */
    public data class ByRole(
        public val filterOptEventsDataEventsRoleRoleFilter: FilterOptEventsDataEventsRoleRoleFilter
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByRole>, ScaleWriter<ByRole> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): ByRole = try {
                ByRole(
                    FilterOptEventsDataEventsRoleRoleFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByRole) = try {
                FilterOptEventsDataEventsRoleRoleFilter.write(
                    writer,
                    instance.filterOptEventsDataEventsRoleRoleFilter
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<EntityFilter>, ScaleWriter<EntityFilter> {
        public override fun read(reader: ScaleCodecReader): EntityFilter = when (
            val discriminant =
                reader.readUByte().toInt()
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
