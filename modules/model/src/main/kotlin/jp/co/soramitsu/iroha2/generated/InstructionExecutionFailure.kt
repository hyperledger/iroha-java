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
 * InstructionExecutionFailure
 *
 * Generated from 'InstructionExecutionFailure' enum
 */
public sealed class InstructionExecutionFailure : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Evaluate' variant
     */
    public data class Evaluate(
        public val instructionEvaluationError: InstructionEvaluationError
    ) : InstructionExecutionFailure() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Evaluate>, ScaleWriter<Evaluate> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Evaluate = try {
                Evaluate(
                    InstructionEvaluationError.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Evaluate) = try {
                InstructionEvaluationError.write(writer, instance.instructionEvaluationError)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Query' variant
     */
    public data class Query(
        public val queryExecutionFailure: QueryExecutionFailure
    ) : InstructionExecutionFailure() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Query>, ScaleWriter<Query> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Query = try {
                Query(
                    QueryExecutionFailure.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Query) = try {
                QueryExecutionFailure.write(writer, instance.queryExecutionFailure)
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
    ) : InstructionExecutionFailure() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Conversion>, ScaleWriter<Conversion> {
            public const val DISCRIMINANT: Int = 2

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

    /**
     * 'Find' variant
     */
    public data class Find(
        public val findError: FindError
    ) : InstructionExecutionFailure() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Find>, ScaleWriter<Find> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Find = try {
                Find(
                    FindError.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Find) = try {
                FindError.write(writer, instance.findError)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Repetition' variant
     */
    public data class Repetition(
        public val repetitionError: RepetitionError
    ) : InstructionExecutionFailure() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Repetition>, ScaleWriter<Repetition> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): Repetition = try {
                Repetition(
                    RepetitionError.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Repetition) = try {
                RepetitionError.write(writer, instance.repetitionError)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Mintability' variant
     */
    public data class Mintability(
        public val mintabilityError: MintabilityError
    ) : InstructionExecutionFailure() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Mintability>, ScaleWriter<Mintability> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): Mintability = try {
                Mintability(
                    MintabilityError.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Mintability) = try {
                MintabilityError.write(writer, instance.mintabilityError)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Math' variant
     */
    public data class Math(
        public val mathError: MathError
    ) : InstructionExecutionFailure() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Math>, ScaleWriter<Math> {
            public const val DISCRIMINANT: Int = 6

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
     * 'Metadata' variant
     */
    public data class Metadata(
        public val metadataError: MetadataError
    ) : InstructionExecutionFailure() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Metadata>, ScaleWriter<Metadata> {
            public const val DISCRIMINANT: Int = 7

            public override fun read(reader: ScaleCodecReader): Metadata = try {
                Metadata(
                    MetadataError.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Metadata) = try {
                MetadataError.write(writer, instance.metadataError)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Fail' variant
     */
    public data class Fail(
        public val string: String
    ) : InstructionExecutionFailure() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Fail>, ScaleWriter<Fail> {
            public const val DISCRIMINANT: Int = 8

            public override fun read(reader: ScaleCodecReader): Fail = try {
                Fail(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Fail) = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'InvalidParameter' variant
     */
    public data class InvalidParameter(
        public val invalidParameterError: InvalidParameterError
    ) : InstructionExecutionFailure() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<InvalidParameter>, ScaleWriter<InvalidParameter> {
            public const val DISCRIMINANT: Int = 9

            public override fun read(reader: ScaleCodecReader): InvalidParameter = try {
                InvalidParameter(
                    InvalidParameterError.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: InvalidParameter) = try {
                InvalidParameterError.write(writer, instance.invalidParameterError)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'InvariantViolation' variant
     */
    public data class InvariantViolation(
        public val string: String
    ) : InstructionExecutionFailure() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<InvariantViolation>, ScaleWriter<InvariantViolation> {
            public const val DISCRIMINANT: Int = 10

            public override fun read(reader: ScaleCodecReader): InvariantViolation = try {
                InvariantViolation(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: InvariantViolation) = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object :
        ScaleReader<InstructionExecutionFailure>,
        ScaleWriter<InstructionExecutionFailure> {
        public override fun read(reader: ScaleCodecReader): InstructionExecutionFailure = when (
            val
            discriminant = reader.readUByte()
        ) {
            0 -> Evaluate.read(reader)
            1 -> Query.read(reader)
            2 -> Conversion.read(reader)
            3 -> Find.read(reader)
            4 -> Repetition.read(reader)
            5 -> Mintability.read(reader)
            6 -> Math.read(reader)
            7 -> Metadata.read(reader)
            8 -> Fail.read(reader)
            9 -> InvalidParameter.read(reader)
            10 -> InvariantViolation.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: InstructionExecutionFailure) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Evaluate.write(writer, instance as Evaluate)
                1 -> Query.write(writer, instance as Query)
                2 -> Conversion.write(writer, instance as Conversion)
                3 -> Find.write(writer, instance as Find)
                4 -> Repetition.write(writer, instance as Repetition)
                5 -> Mintability.write(writer, instance as Mintability)
                6 -> Math.write(writer, instance as Math)
                7 -> Metadata.write(writer, instance as Metadata)
                8 -> Fail.write(writer, instance as Fail)
                9 -> InvalidParameter.write(writer, instance as InvalidParameter)
                10 -> InvariantViolation.write(writer, instance as InvariantViolation)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
