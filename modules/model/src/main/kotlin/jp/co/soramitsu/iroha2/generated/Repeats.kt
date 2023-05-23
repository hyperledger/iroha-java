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
import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.Long

/**
 * Repeats
 *
 * Generated from 'Repeats' enum
 */
public sealed class Repeats : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is Indefinitely -> Indefinitely.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is Indefinitely -> Indefinitely.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'Indefinitely' variant
     */
    public class Indefinitely : Repeats() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Indefinitely>, ScaleWriter<Indefinitely> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Indefinitely = try {
                Indefinitely()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Indefinitely) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Indefinitely, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".Repeats.Indefinitely".hashCode()
        }
    }

    /**
     * 'Exactly' variant
     */
    public data class Exactly(
        public val u32: Long
    ) : Repeats() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Exactly>, ScaleWriter<Exactly> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Exactly = try {
                Exactly(
                    reader.readUint32(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Exactly) = try {
                writer.writeUint32(instance.u32)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Repeats>, ScaleWriter<Repeats> {
        public override fun read(reader: ScaleCodecReader): Repeats = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Indefinitely.read(reader)
            1 -> Exactly.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Repeats) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Indefinitely.write(writer, instance as Indefinitely)
                1 -> Exactly.write(writer, instance as Exactly)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
