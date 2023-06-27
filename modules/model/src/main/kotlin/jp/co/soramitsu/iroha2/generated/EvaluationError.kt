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
import kotlin.String

/**
 * EvaluationError
 *
 * Generated from 'EvaluationError' enum
 */
public sealed class EvaluationError : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Math' variant
     */
    public data class Math(
        public val mathError: MathError
    ) : EvaluationError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Math>, ScaleWriter<Math> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Math = try {
                Math(
                    MathError.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Math) = try {
                MathError.write(writer, instance.mathError)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Validation' variant
     */
    public data class Validation(
        public val validationFail: ValidationFail
    ) : EvaluationError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Validation>, ScaleWriter<Validation> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Validation = try {
                Validation(
                    ValidationFail.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Validation) = try {
                ValidationFail.write(writer, instance.validationFail)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Find' variant
     */
    public data class Find(
        public val string: String
    ) : EvaluationError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Find>, ScaleWriter<Find> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Find = try {
                Find(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Find) = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Conversion' variant
     */
    public data class Conversion(
        public val string: String
    ) : EvaluationError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Conversion>, ScaleWriter<Conversion> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Conversion = try {
                Conversion(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Conversion) = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<EvaluationError>, ScaleWriter<EvaluationError> {
        public override fun read(reader: ScaleCodecReader): EvaluationError = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Math.read(reader)
            1 -> Validation.read(reader)
            2 -> Find.read(reader)
            3 -> Conversion.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: EvaluationError) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Math.write(writer, instance as Math)
                1 -> Validation.write(writer, instance as Validation)
                2 -> Find.write(writer, instance as Find)
                3 -> Conversion.write(writer, instance as Conversion)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
