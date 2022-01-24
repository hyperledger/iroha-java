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
 * Status
 *
 * Generated from 'iroha_data_model::events::data::Status' enum
 */
public sealed class Status : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Created' variant
     */
    public class Created : Status() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Created>, ScaleWriter<Created> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Created = try {
                Created()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Created) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Updated' variant
     */
    public data class Updated(
        public val updated: jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.Updated
    ) : Status() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Updated>, ScaleWriter<Updated> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Updated = try {
                Updated(
                    jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.Updated.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Updated) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.Updated.write(
                    writer,
                    instance.updated
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Deleted' variant
     */
    public class Deleted : Status() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Deleted>, ScaleWriter<Deleted> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Deleted = try {
                Deleted()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Deleted) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Status>, ScaleWriter<Status> {
        public override fun read(reader: ScaleCodecReader): Status = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Created.read(reader)
            1 -> Updated.read(reader)
            2 -> Deleted.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Status) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Created.write(writer, instance as Created)
                1 -> Updated.write(writer, instance as Updated)
                2 -> Deleted.write(writer, instance as Deleted)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
