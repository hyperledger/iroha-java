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
 * SemiRange
 *
 * Generated from 'SemiRange' enum
 */
public sealed class SemiRange : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'U32' variant
     */
    public data class U32(
        public val semiIntervalOfu32: SemiIntervalOfu32
    ) : SemiRange() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<U32>, ScaleWriter<U32> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): U32 = try {
                U32(
                    SemiIntervalOfu32.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: U32) = try {
                SemiIntervalOfu32.write(writer, instance.semiIntervalOfu32)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'U128' variant
     */
    public data class U128(
        public val semiIntervalOfu128: SemiIntervalOfu128
    ) : SemiRange() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<U128>, ScaleWriter<U128> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): U128 = try {
                U128(
                    SemiIntervalOfu128.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: U128) = try {
                SemiIntervalOfu128.write(writer, instance.semiIntervalOfu128)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Fixed' variant
     */
    public data class Fixed(
        public val semiIntervalOfFixed: SemiIntervalOfFixed
    ) : SemiRange() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Fixed>, ScaleWriter<Fixed> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Fixed = try {
                Fixed(
                    SemiIntervalOfFixed.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Fixed) = try {
                SemiIntervalOfFixed.write(writer, instance.semiIntervalOfFixed)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<SemiRange>, ScaleWriter<SemiRange> {
        public override fun read(reader: ScaleCodecReader): SemiRange = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> U32.read(reader)
            1 -> U128.read(reader)
            2 -> Fixed.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: SemiRange) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> U32.write(writer, instance as U32)
                1 -> U128.write(writer, instance as U128)
                2 -> Fixed.write(writer, instance as Fixed)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
