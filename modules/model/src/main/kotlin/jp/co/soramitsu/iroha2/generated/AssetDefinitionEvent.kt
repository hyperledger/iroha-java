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
 * AssetDefinitionEvent
 *
 * Generated from 'AssetDefinitionEvent' enum
 */
public sealed class AssetDefinitionEvent : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Created' variant
     */
    public data class Created(
        public val assetDefinition: AssetDefinition
    ) : AssetDefinitionEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Created>, ScaleWriter<Created> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Created = try {
                Created(
                    AssetDefinition.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Created) = try {
                AssetDefinition.write(writer, instance.assetDefinition)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'MintabilityChanged' variant
     */
    public data class MintabilityChanged(
        public val assetDefinitionId: AssetDefinitionId
    ) : AssetDefinitionEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<MintabilityChanged>, ScaleWriter<MintabilityChanged> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): MintabilityChanged = try {
                MintabilityChanged(
                    AssetDefinitionId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: MintabilityChanged) = try {
                AssetDefinitionId.write(writer, instance.assetDefinitionId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'OwnerChanged' variant
     */
    public data class OwnerChanged(
        public val assetDefinitionOwnerChanged: AssetDefinitionOwnerChanged
    ) : AssetDefinitionEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<OwnerChanged>, ScaleWriter<OwnerChanged> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): OwnerChanged = try {
                OwnerChanged(
                    AssetDefinitionOwnerChanged.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: OwnerChanged) = try {
                AssetDefinitionOwnerChanged.write(writer, instance.assetDefinitionOwnerChanged)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Deleted' variant
     */
    public data class Deleted(
        public val assetDefinitionId: AssetDefinitionId
    ) : AssetDefinitionEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Deleted>, ScaleWriter<Deleted> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Deleted = try {
                Deleted(
                    AssetDefinitionId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Deleted) = try {
                AssetDefinitionId.write(writer, instance.assetDefinitionId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'MetadataInserted' variant
     */
    public data class MetadataInserted(
        public val metadataChanged: MetadataChanged<AssetDefinitionId>
    ) : AssetDefinitionEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<MetadataInserted>, ScaleWriter<MetadataInserted> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): MetadataInserted = try {
                MetadataInserted(
                    MetadataChanged.read(reader) as MetadataChanged<AssetDefinitionId>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: MetadataInserted) = try {
                MetadataChanged.write(writer, instance.metadataChanged)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'MetadataRemoved' variant
     */
    public data class MetadataRemoved(
        public val metadataChanged: MetadataChanged<AssetDefinitionId>
    ) : AssetDefinitionEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<MetadataRemoved>, ScaleWriter<MetadataRemoved> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): MetadataRemoved = try {
                MetadataRemoved(
                    MetadataChanged.read(reader) as MetadataChanged<AssetDefinitionId>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: MetadataRemoved) = try {
                MetadataChanged.write(writer, instance.metadataChanged)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'TotalQuantityChanged' variant
     */
    public data class TotalQuantityChanged(
        public val assetDefinitionTotalQuantityChanged: AssetDefinitionTotalQuantityChanged
    ) : AssetDefinitionEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<TotalQuantityChanged>, ScaleWriter<TotalQuantityChanged> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): TotalQuantityChanged = try {
                TotalQuantityChanged(
                    AssetDefinitionTotalQuantityChanged.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: TotalQuantityChanged) = try {
                AssetDefinitionTotalQuantityChanged.write(
                    writer,
                    instance.assetDefinitionTotalQuantityChanged
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<AssetDefinitionEvent>, ScaleWriter<AssetDefinitionEvent> {
        public override fun read(reader: ScaleCodecReader): AssetDefinitionEvent = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Created.read(reader)
            1 -> MintabilityChanged.read(reader)
            2 -> OwnerChanged.read(reader)
            3 -> Deleted.read(reader)
            4 -> MetadataInserted.read(reader)
            5 -> MetadataRemoved.read(reader)
            6 -> TotalQuantityChanged.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetDefinitionEvent) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Created.write(writer, instance as Created)
                1 -> MintabilityChanged.write(writer, instance as MintabilityChanged)
                2 -> OwnerChanged.write(writer, instance as OwnerChanged)
                3 -> Deleted.write(writer, instance as Deleted)
                4 -> MetadataInserted.write(writer, instance as MetadataInserted)
                5 -> MetadataRemoved.write(writer, instance as MetadataRemoved)
                6 -> TotalQuantityChanged.write(writer, instance as TotalQuantityChanged)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
