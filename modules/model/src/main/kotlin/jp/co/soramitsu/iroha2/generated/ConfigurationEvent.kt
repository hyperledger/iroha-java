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
 * ConfigurationEvent
 *
 * Generated from 'ConfigurationEvent' enum
 */
public sealed class ConfigurationEvent : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Changed' variant
     */
    public data class Changed(
        public val parameterId: ParameterId,
    ) : ConfigurationEvent() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ConfigurationEvent.Changed>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ConfigurationEvent.Changed> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ConfigurationEvent.Changed = try {
                Changed(
                    ParameterId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ConfigurationEvent.Changed,
            ): Unit = try {
                ParameterId.write(writer, instance.parameterId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Created' variant
     */
    public data class Created(
        public val parameterId: ParameterId,
    ) : ConfigurationEvent() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ConfigurationEvent.Created>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ConfigurationEvent.Created> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ConfigurationEvent.Created = try {
                Created(
                    ParameterId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ConfigurationEvent.Created,
            ): Unit = try {
                ParameterId.write(writer, instance.parameterId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Deleted' variant
     */
    public data class Deleted(
        public val parameterId: ParameterId,
    ) : ConfigurationEvent() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ConfigurationEvent.Deleted>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ConfigurationEvent.Deleted> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ConfigurationEvent.Deleted = try {
                Deleted(
                    ParameterId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ConfigurationEvent.Deleted,
            ): Unit = try {
                ParameterId.write(writer, instance.parameterId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<ConfigurationEvent>, ScaleWriter<ConfigurationEvent> {
        override fun read(reader: ScaleCodecReader): ConfigurationEvent = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Changed.read(reader)
            1 -> Created.read(reader)
            2 -> Deleted.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: ConfigurationEvent) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Changed.write(writer, instance as Changed)
                1 -> Created.write(writer, instance as Created)
                2 -> Deleted.write(writer, instance as Deleted)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
