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
 * MintabilityError
 *
 * Generated from 'MintabilityError' enum
 */
public sealed class MintabilityError : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is MintUnmintable -> MintUnmintable.equals(this, other)
        is ForbidMintOnMintable -> ForbidMintOnMintable.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is MintUnmintable -> MintUnmintable.hashCode()
        is ForbidMintOnMintable -> ForbidMintOnMintable.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'MintUnmintable' variant
     */
    public class MintUnmintable : MintabilityError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<MintUnmintable>, ScaleWriter<MintUnmintable> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): MintUnmintable = try {
                MintUnmintable()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: MintUnmintable) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: MintUnmintable, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".MintabilityError.MintUnmintable".hashCode()
        }
    }

    /**
     * 'ForbidMintOnMintable' variant
     */
    public class ForbidMintOnMintable : MintabilityError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ForbidMintOnMintable>, ScaleWriter<ForbidMintOnMintable> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): ForbidMintOnMintable = try {
                ForbidMintOnMintable()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ForbidMintOnMintable) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: ForbidMintOnMintable, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".MintabilityError.ForbidMintOnMintable".hashCode()
        }
    }

    public companion object : ScaleReader<MintabilityError>, ScaleWriter<MintabilityError> {
        public override fun read(reader: ScaleCodecReader): MintabilityError = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> MintUnmintable.read(reader)
            1 -> ForbidMintOnMintable.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: MintabilityError) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> MintUnmintable.write(writer, instance as MintUnmintable)
                1 -> ForbidMintOnMintable.write(writer, instance as ForbidMintOnMintable)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
