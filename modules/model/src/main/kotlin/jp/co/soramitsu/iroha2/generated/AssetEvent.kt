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
 * AssetEvent
 *
 * Generated from 'AssetEvent' enum
 */
public sealed class AssetEvent : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Created' variant
     */
    public data class Created(
        public val asset: Asset
    ) : AssetEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Created>, ScaleWriter<Created> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Created = try {
                Created(
                    Asset.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Created) = try {
                Asset.write(writer, instance.asset)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Deleted' variant
     */
    public data class Deleted(
        public val assetId: AssetId
    ) : AssetEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Deleted>, ScaleWriter<Deleted> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Deleted = try {
                Deleted(
                    AssetId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Deleted) = try {
                AssetId.write(writer, instance.assetId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Added' variant
     */
    public data class Added(
        public val assetChanged: AssetChanged
    ) : AssetEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Added>, ScaleWriter<Added> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Added = try {
                Added(
                    AssetChanged.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Added) = try {
                AssetChanged.write(writer, instance.assetChanged)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Removed' variant
     */
    public data class Removed(
        public val assetChanged: AssetChanged
    ) : AssetEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Removed>, ScaleWriter<Removed> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Removed = try {
                Removed(
                    AssetChanged.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Removed) = try {
                AssetChanged.write(writer, instance.assetChanged)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'MetadataInserted' variant
     */
    public data class MetadataInserted(
        public val metadataChanged: MetadataChanged<AssetId>
    ) : AssetEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<MetadataInserted>, ScaleWriter<MetadataInserted> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): MetadataInserted = try {
                MetadataInserted(
                    MetadataChanged.read(reader) as MetadataChanged<AssetId>,
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
        public val metadataChanged: MetadataChanged<AssetId>
    ) : AssetEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<MetadataRemoved>, ScaleWriter<MetadataRemoved> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): MetadataRemoved = try {
                MetadataRemoved(
                    MetadataChanged.read(reader) as MetadataChanged<AssetId>,
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

    public companion object : ScaleReader<AssetEvent>, ScaleWriter<AssetEvent> {
        public override fun read(reader: ScaleCodecReader): AssetEvent = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Created.read(reader)
            1 -> Deleted.read(reader)
            2 -> Added.read(reader)
            3 -> Removed.read(reader)
            4 -> MetadataInserted.read(reader)
            5 -> MetadataRemoved.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetEvent) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Created.write(writer, instance as Created)
                1 -> Deleted.write(writer, instance as Deleted)
                2 -> Added.write(writer, instance as Added)
                3 -> Removed.write(writer, instance as Removed)
                4 -> MetadataInserted.write(writer, instance as MetadataInserted)
                5 -> MetadataRemoved.write(writer, instance as MetadataRemoved)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
