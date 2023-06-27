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
import kotlin.String

/**
 * MathError
 *
 * Generated from 'MathError' enum
 */
public sealed class MathError : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is Overflow -> Overflow.equals(this, other)
        is NotEnoughQuantity -> NotEnoughQuantity.equals(this, other)
        is DivideByZero -> DivideByZero.equals(this, other)
        is NegativeValue -> NegativeValue.equals(this, other)
        is DomainViolation -> DomainViolation.equals(this, other)
        is Unknown -> Unknown.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is Overflow -> Overflow.hashCode()
        is NotEnoughQuantity -> NotEnoughQuantity.hashCode()
        is DivideByZero -> DivideByZero.hashCode()
        is NegativeValue -> NegativeValue.hashCode()
        is DomainViolation -> DomainViolation.hashCode()
        is Unknown -> Unknown.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'Overflow' variant
     */
    public class Overflow : MathError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Overflow>, ScaleWriter<Overflow> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Overflow = try {
                Overflow()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Overflow) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Overflow, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".MathError.Overflow".hashCode()
        }
    }

    /**
     * 'NotEnoughQuantity' variant
     */
    public class NotEnoughQuantity : MathError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<NotEnoughQuantity>, ScaleWriter<NotEnoughQuantity> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): NotEnoughQuantity = try {
                NotEnoughQuantity()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: NotEnoughQuantity) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: NotEnoughQuantity, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".MathError.NotEnoughQuantity".hashCode()
        }
    }

    /**
     * 'DivideByZero' variant
     */
    public class DivideByZero : MathError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<DivideByZero>, ScaleWriter<DivideByZero> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): DivideByZero = try {
                DivideByZero()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: DivideByZero) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: DivideByZero, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".MathError.DivideByZero".hashCode()
        }
    }

    /**
     * 'NegativeValue' variant
     */
    public class NegativeValue : MathError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<NegativeValue>, ScaleWriter<NegativeValue> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): NegativeValue = try {
                NegativeValue()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: NegativeValue) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: NegativeValue, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".MathError.NegativeValue".hashCode()
        }
    }

    /**
     * 'DomainViolation' variant
     */
    public class DomainViolation : MathError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<DomainViolation>, ScaleWriter<DomainViolation> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): DomainViolation = try {
                DomainViolation()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: DomainViolation) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: DomainViolation, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".MathError.DomainViolation".hashCode()
        }
    }

    /**
     * 'Unknown' variant
     */
    public class Unknown : MathError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Unknown>, ScaleWriter<Unknown> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): Unknown = try {
                Unknown()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Unknown) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Unknown, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".MathError.Unknown".hashCode()
        }
    }

    /**
     * 'BinaryOpIncompatibleNumericValueTypes' variant
     */
    public data class BinaryOpIncompatibleNumericValueTypes(
        public val binaryOpIncompatibleNumericValueTypesError:
            BinaryOpIncompatibleNumericValueTypesError
    ) : MathError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<BinaryOpIncompatibleNumericValueTypes>,
            ScaleWriter<BinaryOpIncompatibleNumericValueTypes> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): BinaryOpIncompatibleNumericValueTypes =
                try {
                    BinaryOpIncompatibleNumericValueTypes(
                        BinaryOpIncompatibleNumericValueTypesError.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            public override fun write(
                writer: ScaleCodecWriter,
                instance: BinaryOpIncompatibleNumericValueTypes
            ) = try {
                BinaryOpIncompatibleNumericValueTypesError.write(
                    writer,
                    instance.binaryOpIncompatibleNumericValueTypesError
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FixedPointConversion' variant
     */
    public data class FixedPointConversion(
        public val string: String
    ) : MathError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<FixedPointConversion>, ScaleWriter<FixedPointConversion> {
            public const val DISCRIMINANT: Int = 7

            public override fun read(reader: ScaleCodecReader): FixedPointConversion = try {
                FixedPointConversion(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: FixedPointConversion) = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<MathError>, ScaleWriter<MathError> {
        public override fun read(reader: ScaleCodecReader): MathError = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Overflow.read(reader)
            1 -> NotEnoughQuantity.read(reader)
            2 -> DivideByZero.read(reader)
            3 -> NegativeValue.read(reader)
            4 -> DomainViolation.read(reader)
            5 -> Unknown.read(reader)
            6 -> BinaryOpIncompatibleNumericValueTypes.read(reader)
            7 -> FixedPointConversion.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: MathError) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Overflow.write(writer, instance as Overflow)
                1 -> NotEnoughQuantity.write(writer, instance as NotEnoughQuantity)
                2 -> DivideByZero.write(writer, instance as DivideByZero)
                3 -> NegativeValue.write(writer, instance as NegativeValue)
                4 -> DomainViolation.write(writer, instance as DomainViolation)
                5 -> Unknown.write(writer, instance as Unknown)
                6 -> BinaryOpIncompatibleNumericValueTypes.write(
                    writer,
                    instance as
                        BinaryOpIncompatibleNumericValueTypes
                )
                7 -> FixedPointConversion.write(writer, instance as FixedPointConversion)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
