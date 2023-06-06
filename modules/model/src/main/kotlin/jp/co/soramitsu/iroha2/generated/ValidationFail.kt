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
 * ValidationFail
 *
 * Generated from 'ValidationFail' enum
 */
public sealed class ValidationFail : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is TooComplex -> TooComplex.equals(this, other)
        is InternalError -> InternalError.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is TooComplex -> TooComplex.hashCode()
        is InternalError -> InternalError.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'NotPermitted' variant
     */
    public data class NotPermitted(
        public val string: String
    ) : ValidationFail() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<NotPermitted>, ScaleWriter<NotPermitted> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): NotPermitted = try {
                NotPermitted(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: NotPermitted) = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'InstructionFailed' variant
     */
    public data class InstructionFailed(
        public val instructionExecutionFailure: InstructionExecutionFailure
    ) : ValidationFail() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<InstructionFailed>, ScaleWriter<InstructionFailed> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): InstructionFailed = try {
                InstructionFailed(
                    InstructionExecutionFailure.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: InstructionFailed) = try {
                InstructionExecutionFailure.write(writer, instance.instructionExecutionFailure)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'QueryFailed' variant
     */
    public data class QueryFailed(
        public val queryExecutionFailure: QueryExecutionFailure
    ) : ValidationFail() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<QueryFailed>, ScaleWriter<QueryFailed> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): QueryFailed = try {
                QueryFailed(
                    QueryExecutionFailure.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: QueryFailed) = try {
                QueryExecutionFailure.write(writer, instance.queryExecutionFailure)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'TooComplex' variant
     */
    public class TooComplex : ValidationFail() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<TooComplex>, ScaleWriter<TooComplex> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): TooComplex = try {
                TooComplex()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: TooComplex) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: TooComplex, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".ValidationFail.TooComplex".hashCode()
        }
    }

    /**
     * 'InternalError' variant
     */
    public class InternalError : ValidationFail() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<InternalError>, ScaleWriter<InternalError> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): InternalError = try {
                InternalError()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: InternalError) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: InternalError, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".ValidationFail.InternalError".hashCode()
        }
    }

    public companion object : ScaleReader<ValidationFail>, ScaleWriter<ValidationFail> {
        public override fun read(reader: ScaleCodecReader): ValidationFail = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> NotPermitted.read(reader)
            1 -> InstructionFailed.read(reader)
            2 -> QueryFailed.read(reader)
            3 -> TooComplex.read(reader)
            4 -> InternalError.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: ValidationFail) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> NotPermitted.write(writer, instance as NotPermitted)
                1 -> InstructionFailed.write(writer, instance as InstructionFailed)
                2 -> QueryFailed.write(writer, instance as QueryFailed)
                3 -> TooComplex.write(writer, instance as TooComplex)
                4 -> InternalError.write(writer, instance as InternalError)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
