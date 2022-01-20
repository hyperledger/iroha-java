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
 * MetadataUpdated
 *
 * Generated from 'iroha_data_model::events::data::MetadataUpdated' enum
 */
public sealed class MetadataUpdated : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Inserted' variant
     */
    public class Inserted : MetadataUpdated() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Inserted>, ScaleWriter<Inserted> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Inserted = try {
                Inserted()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Inserted) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Removed' variant
     */
    public class Removed : MetadataUpdated() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Removed>, ScaleWriter<Removed> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Removed = try {
                Removed()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Removed) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<MetadataUpdated>, ScaleWriter<MetadataUpdated> {
        public override fun read(reader: ScaleCodecReader): MetadataUpdated = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Inserted.read(reader)
            1 -> Removed.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: MetadataUpdated) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Inserted.write(writer, instance as Inserted)
                1 -> Removed.write(writer, instance as Removed)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
