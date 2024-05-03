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
 * ParameterValueBox
 *
 * Generated from 'ParameterValueBox' enum
 */
public sealed class ParameterValueBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'TransactionLimits' variant
     */
    public data class TransactionLimits(
        public val transactionLimits: jp.co.soramitsu.iroha2.generated.TransactionLimits,
    ) : ParameterValueBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ParameterValueBox.TransactionLimits>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ParameterValueBox.TransactionLimits> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ParameterValueBox.TransactionLimits = try {
                TransactionLimits(
                    jp.co.soramitsu.iroha2.generated.TransactionLimits.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ParameterValueBox.TransactionLimits,
            ): Unit =
                try {
                    jp.co.soramitsu.iroha2.generated.TransactionLimits.write(writer, instance.transactionLimits)
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }
        }
    }

    /**
     * 'MetadataLimits' variant
     */
    public data class MetadataLimits(
        public val limits: Limits,
    ) : ParameterValueBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ParameterValueBox.MetadataLimits>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ParameterValueBox.MetadataLimits> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ParameterValueBox.MetadataLimits = try {
                MetadataLimits(
                    Limits.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ParameterValueBox.MetadataLimits,
            ): Unit = try {
                Limits.write(writer, instance.limits)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'LengthLimits' variant
     */
    public data class LengthLimits(
        public val lengthLimits: jp.co.soramitsu.iroha2.generated.LengthLimits,
    ) : ParameterValueBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ParameterValueBox.LengthLimits>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ParameterValueBox.LengthLimits> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ParameterValueBox.LengthLimits = try {
                LengthLimits(
                    jp.co.soramitsu.iroha2.generated.LengthLimits.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ParameterValueBox.LengthLimits,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.LengthLimits.write(writer, instance.lengthLimits)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Numeric' variant
     */
    public data class Numeric(
        public val numeric: jp.co.soramitsu.iroha2.generated.Numeric,
    ) : ParameterValueBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ParameterValueBox.Numeric>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ParameterValueBox.Numeric> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ParameterValueBox.Numeric = try {
                Numeric(
                    jp.co.soramitsu.iroha2.generated.Numeric.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ParameterValueBox.Numeric,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Numeric.write(writer, instance.numeric)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<ParameterValueBox>, ScaleWriter<ParameterValueBox> {
        override fun read(reader: ScaleCodecReader): ParameterValueBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> TransactionLimits.read(reader)
            1 -> MetadataLimits.read(reader)
            2 -> LengthLimits.read(reader)
            3 -> Numeric.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: ParameterValueBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> TransactionLimits.write(writer, instance as TransactionLimits)
                1 -> MetadataLimits.write(writer, instance as MetadataLimits)
                2 -> LengthLimits.write(writer, instance as LengthLimits)
                3 -> Numeric.write(writer, instance as Numeric)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
