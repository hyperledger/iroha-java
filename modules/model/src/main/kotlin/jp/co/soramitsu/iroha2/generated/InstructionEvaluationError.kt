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
 * InstructionEvaluationError
 *
 * Generated from 'InstructionEvaluationError' enum
 */
public sealed class InstructionEvaluationError : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Expression' variant
     */
    public data class Expression(
        public val evaluationError: EvaluationError
    ) : InstructionEvaluationError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Expression>, ScaleWriter<Expression> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Expression = try {
                Expression(
                    EvaluationError.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Expression) = try {
                EvaluationError.write(writer, instance.evaluationError)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Unsupported' variant
     */
    public data class Unsupported(
        public val instructionType: InstructionType
    ) : InstructionEvaluationError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Unsupported>, ScaleWriter<Unsupported> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Unsupported = try {
                Unsupported(
                    InstructionType.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Unsupported) = try {
                InstructionType.write(writer, instance.instructionType)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'PermissionParameter' variant
     */
    public data class PermissionParameter(
        public val string: String
    ) : InstructionEvaluationError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<PermissionParameter>, ScaleWriter<PermissionParameter> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): PermissionParameter = try {
                PermissionParameter(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: PermissionParameter) = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Type' variant
     */
    public data class Type(
        public val typeError: TypeError
    ) : InstructionEvaluationError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Type>, ScaleWriter<Type> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Type = try {
                Type(
                    TypeError.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Type) = try {
                TypeError.write(writer, instance.typeError)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object :
        ScaleReader<InstructionEvaluationError>,
        ScaleWriter<InstructionEvaluationError> {
        public override fun read(reader: ScaleCodecReader): InstructionEvaluationError = when (
            val
            discriminant = reader.readUByte()
        ) {
            0 -> Expression.read(reader)
            1 -> Unsupported.read(reader)
            2 -> PermissionParameter.read(reader)
            3 -> Type.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: InstructionEvaluationError) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Expression.write(writer, instance as Expression)
                1 -> Unsupported.write(writer, instance as Unsupported)
                2 -> PermissionParameter.write(writer, instance as PermissionParameter)
                3 -> Type.write(writer, instance as Type)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
