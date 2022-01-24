//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * Updated
 *
 * Generated from 'iroha_data_model::events::data::Updated' enum
 */
public sealed class Updated : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Metadata' variant
     */
    public data class Metadata(
        public val metadataUpdated: MetadataUpdated
    ) : Updated() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Metadata>, ScaleWriter<Metadata> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Metadata = try {
                Metadata(
                    MetadataUpdated.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Metadata) = try {
                MetadataUpdated.write(writer, instance.metadataUpdated)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Authentication' variant
     */
    public class Authentication : Updated() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Authentication>, ScaleWriter<Authentication> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Authentication = try {
                Authentication()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Authentication) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Permission' variant
     */
    public class Permission : Updated() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Permission>, ScaleWriter<Permission> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Permission = try {
                Permission()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Permission) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Asset' variant
     */
    public data class Asset(
        public val assetUpdated: AssetUpdated
    ) : Updated() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Asset>, ScaleWriter<Asset> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Asset = try {
                Asset(
                    AssetUpdated.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Asset) = try {
                AssetUpdated.write(writer, instance.assetUpdated)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Updated>, ScaleWriter<Updated> {
        public override fun read(reader: ScaleCodecReader): Updated = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Metadata.read(reader)
            1 -> Authentication.read(reader)
            2 -> Permission.read(reader)
            3 -> Asset.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Updated) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Metadata.write(writer, instance as Metadata)
                1 -> Authentication.write(writer, instance as Authentication)
                2 -> Permission.write(writer, instance as Permission)
                3 -> Asset.write(writer, instance as Asset)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
