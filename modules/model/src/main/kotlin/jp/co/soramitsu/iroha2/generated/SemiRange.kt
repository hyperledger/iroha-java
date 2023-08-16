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
        public val semiIntervalOfu32: SemiIntervalOfu32,
    ) : SemiRange() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.SemiRange.U32>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.SemiRange.U32> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.SemiRange.U32 =
                try {
                    U32(
                        SemiIntervalOfu32.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.SemiRange.U32,
            ): Unit = try {
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
        public val semiIntervalOfu128: SemiIntervalOfu128,
    ) : SemiRange() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.SemiRange.U128>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.SemiRange.U128> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.SemiRange.U128 =
                try {
                    U128(
                        SemiIntervalOfu128.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.SemiRange.U128,
            ): Unit = try {
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
        public val semiIntervalOfFixed: SemiIntervalOfFixed,
    ) : SemiRange() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.SemiRange.Fixed>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.SemiRange.Fixed> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.SemiRange.Fixed = try {
                Fixed(
                    SemiIntervalOfFixed.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.SemiRange.Fixed,
            ): Unit = try {
                SemiIntervalOfFixed.write(writer, instance.semiIntervalOfFixed)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<SemiRange>, ScaleWriter<SemiRange> {
        override fun read(reader: ScaleCodecReader): SemiRange = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> U32.read(reader)
            1 -> U128.read(reader)
            2 -> Fixed.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: SemiRange) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> U32.write(writer, instance as U32)
                1 -> U128.write(writer, instance as U128)
                2 -> Fixed.write(writer, instance as Fixed)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
