//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.smartcontracts.isi.permissions

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * ValidatorType
 *
 * Generated from 'iroha_core::smartcontracts::isi::permissions::ValidatorType' enum
 */
public sealed class ValidatorType : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Instruction' variant
     */
    public class Instruction : ValidatorType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Instruction>, ScaleWriter<Instruction> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Instruction = try {
                Instruction()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Instruction) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Query' variant
     */
    public class Query : ValidatorType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Query>, ScaleWriter<Query> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Query = try {
                Query()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Query) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Expression' variant
     */
    public class Expression : ValidatorType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Expression>, ScaleWriter<Expression> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Expression = try {
                Expression()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Expression) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<ValidatorType>, ScaleWriter<ValidatorType> {
        public override fun read(reader: ScaleCodecReader): ValidatorType = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> Instruction.read(reader)
            1 -> Query.read(reader)
            2 -> Expression.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: ValidatorType) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Instruction.write(writer, instance as Instruction)
                1 -> Query.write(writer, instance as Query)
                2 -> Expression.write(writer, instance as Expression)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
