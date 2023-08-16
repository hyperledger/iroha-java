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
import java.math.BigInteger
import kotlin.Int
import kotlin.Long
import kotlin.Unit

/**
 * NumericValue
 *
 * Generated from 'NumericValue' enum
 */
public sealed class NumericValue : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'U32' variant
     */
    public data class U32(
        public val u32: Long,
    ) : NumericValue() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.NumericValue.U32>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.NumericValue.U32> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.NumericValue.U32 = try {
                U32(
                    reader.readUint32(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.NumericValue.U32,
            ): Unit = try {
                writer.writeUint32(instance.u32)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'U64' variant
     */
    public data class U64(
        public val u64: BigInteger,
    ) : NumericValue() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.NumericValue.U64>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.NumericValue.U64> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.NumericValue.U64 = try {
                U64(
                    reader.readUint64(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.NumericValue.U64,
            ): Unit = try {
                writer.writeUint64(instance.u64)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'U128' variant
     */
    public data class U128(
        public val u128: BigInteger,
    ) : NumericValue() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.NumericValue.U128>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.NumericValue.U128> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.NumericValue.U128 = try {
                U128(
                    reader.readUint128(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.NumericValue.U128,
            ): Unit = try {
                writer.writeUint128(instance.u128)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Fixed' variant
     */
    public data class Fixed(
        public val fixed: jp.co.soramitsu.iroha2.generated.Fixed,
    ) : NumericValue() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.NumericValue.Fixed>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.NumericValue.Fixed> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.NumericValue.Fixed = try {
                Fixed(
                    jp.co.soramitsu.iroha2.generated.Fixed.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.NumericValue.Fixed,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Fixed.write(writer, instance.fixed)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<NumericValue>, ScaleWriter<NumericValue> {
        override fun read(reader: ScaleCodecReader): NumericValue = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> U32.read(reader)
            1 -> U64.read(reader)
            2 -> U128.read(reader)
            3 -> Fixed.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: NumericValue) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> U32.write(writer, instance as U32)
                1 -> U64.write(writer, instance as U64)
                2 -> U128.write(writer, instance as U128)
                3 -> Fixed.write(writer, instance as Fixed)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
