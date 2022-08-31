//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.predicate.numerical

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigInteger
import kotlin.Int
import kotlin.Long

/**
 * Range
 *
 * Generated from 'iroha_data_model::predicate::numerical::Range' enum
 */
public sealed class Range : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'U32' variant
     */
    public data class U32(
        public val semiInterval: SemiInterval<Long>
    ) : Range() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<U32>, ScaleWriter<U32> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): U32 = try {
                U32(
                    SemiInterval.read(reader) as SemiInterval<Long>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: U32) = try {
                SemiInterval.write(writer, instance.semiInterval)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'U128' variant
     */
    public data class U128(
        public val semiInterval: SemiInterval<BigInteger>
    ) : Range() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<U128>, ScaleWriter<U128> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): U128 = try {
                U128(
                    SemiInterval.read(reader) as SemiInterval<BigInteger>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: U128) = try {
                SemiInterval.write(writer, instance.semiInterval)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Fixed' variant
     */
    public data class Fixed(
        public val semiInterval: SemiInterval<jp.co.soramitsu.iroha2.generated.primitives.fixed.Fixed>
    ) : Range() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Fixed>, ScaleWriter<Fixed> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Fixed = try {
                Fixed(
                    SemiInterval.read(reader) as
                        SemiInterval<jp.co.soramitsu.iroha2.generated.primitives.fixed.Fixed>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Fixed) = try {
                SemiInterval.write(writer, instance.semiInterval)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Range>, ScaleWriter<Range> {
        public override fun read(reader: ScaleCodecReader): Range = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> U32.read(reader)
            1 -> U128.read(reader)
            2 -> Fixed.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Range) {
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
