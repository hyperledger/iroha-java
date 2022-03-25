//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.trigger

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * TriggerEvent
 *
 * Generated from 'iroha_data_model::events::data::events::trigger::TriggerEvent' enum
 */
public sealed class TriggerEvent : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Created' variant
     */
    public data class Created(
        public val id: Id
    ) : TriggerEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Created>, ScaleWriter<Created> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Created = try {
                Created(
                    Id.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Created) = try {
                Id.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Deleted' variant
     */
    public data class Deleted(
        public val id: Id
    ) : TriggerEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Deleted>, ScaleWriter<Deleted> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Deleted = try {
                Deleted(
                    Id.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Deleted) = try {
                Id.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Extended' variant
     */
    public data class Extended(
        public val id: Id
    ) : TriggerEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Extended>, ScaleWriter<Extended> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Extended = try {
                Extended(
                    Id.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Extended) = try {
                Id.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Shortened' variant
     */
    public data class Shortened(
        public val id: Id
    ) : TriggerEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Shortened>, ScaleWriter<Shortened> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Shortened = try {
                Shortened(
                    Id.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Shortened) = try {
                Id.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<TriggerEvent>, ScaleWriter<TriggerEvent> {
        public override fun read(reader: ScaleCodecReader): TriggerEvent = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> Created.read(reader)
            1 -> Deleted.read(reader)
            2 -> Extended.read(reader)
            3 -> Shortened.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: TriggerEvent) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Created.write(writer, instance as Created)
                1 -> Deleted.write(writer, instance as Deleted)
                2 -> Extended.write(writer, instance as Extended)
                3 -> Shortened.write(writer, instance as Shortened)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
