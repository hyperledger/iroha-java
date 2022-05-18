//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * Mintable
 *
 * Generated from 'iroha_data_model::asset::Mintable' enum
 */
public sealed class Mintable : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Infinitely' variant
     */
    public class Infinitely : Mintable() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Infinitely>, ScaleWriter<Infinitely> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Infinitely = try {
                Infinitely()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Infinitely) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Once' variant
     */
    public class Once : Mintable() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Once>, ScaleWriter<Once> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Once = try {
                Once()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Once) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Not' variant
     */
    public class Not : Mintable() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Not>, ScaleWriter<Not> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Not = try {
                Not()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Not) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Mintable>, ScaleWriter<Mintable> {
        public override fun read(reader: ScaleCodecReader): Mintable = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> Infinitely.read(reader)
            1 -> Once.read(reader)
            2 -> Not.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Mintable) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Infinitely.write(writer, instance as Infinitely)
                1 -> Once.write(writer, instance as Once)
                2 -> Not.write(writer, instance as Not)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
