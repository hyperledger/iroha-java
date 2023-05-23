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

/**
 * Algorithm
 *
 * Generated from 'Algorithm' enum
 */
public sealed class Algorithm : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is Ed25519 -> Ed25519.equals(this, other)
        is Secp256k1 -> Secp256k1.equals(this, other)
        is BlsNormal -> BlsNormal.equals(this, other)
        is BlsSmall -> BlsSmall.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is Ed25519 -> Ed25519.hashCode()
        is Secp256k1 -> Secp256k1.hashCode()
        is BlsNormal -> BlsNormal.hashCode()
        is BlsSmall -> BlsSmall.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'Ed25519' variant
     */
    public class Ed25519 : Algorithm() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Ed25519>, ScaleWriter<Ed25519> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Ed25519 = try {
                Ed25519()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Ed25519) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Ed25519, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".Algorithm.Ed25519".hashCode()
        }
    }

    /**
     * 'Secp256k1' variant
     */
    public class Secp256k1 : Algorithm() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Secp256k1>, ScaleWriter<Secp256k1> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Secp256k1 = try {
                Secp256k1()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Secp256k1) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Secp256k1, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".Algorithm.Secp256k1".hashCode()
        }
    }

    /**
     * 'BlsNormal' variant
     */
    public class BlsNormal : Algorithm() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<BlsNormal>, ScaleWriter<BlsNormal> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): BlsNormal = try {
                BlsNormal()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: BlsNormal) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: BlsNormal, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".Algorithm.BlsNormal".hashCode()
        }
    }

    /**
     * 'BlsSmall' variant
     */
    public class BlsSmall : Algorithm() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<BlsSmall>, ScaleWriter<BlsSmall> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): BlsSmall = try {
                BlsSmall()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: BlsSmall) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: BlsSmall, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".Algorithm.BlsSmall".hashCode()
        }
    }

    public companion object : ScaleReader<Algorithm>, ScaleWriter<Algorithm> {
        public override fun read(reader: ScaleCodecReader): Algorithm = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Ed25519.read(reader)
            1 -> Secp256k1.read(reader)
            2 -> BlsNormal.read(reader)
            3 -> BlsSmall.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Algorithm) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Ed25519.write(writer, instance as Ed25519)
                1 -> Secp256k1.write(writer, instance as Secp256k1)
                2 -> BlsNormal.write(writer, instance as BlsNormal)
                3 -> BlsSmall.write(writer, instance as BlsSmall)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
