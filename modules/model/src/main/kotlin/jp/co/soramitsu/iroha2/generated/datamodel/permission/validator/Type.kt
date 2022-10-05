//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.permission.validator

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * Type
 *
 * Generated from 'iroha_data_model::permission::validator::Type' enum
 */
public sealed class Type : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Transaction' variant
     */
    public class Transaction : Type() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Transaction>, ScaleWriter<Transaction> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Transaction = try {
                Transaction()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Transaction) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Instruction' variant
     */
    public class Instruction : Type() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Instruction>, ScaleWriter<Instruction> {
            public const val DISCRIMINANT: Int = 1

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
    public class Query : Type() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Query>, ScaleWriter<Query> {
            public const val DISCRIMINANT: Int = 2

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
    public class Expression : Type() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Expression>, ScaleWriter<Expression> {
            public const val DISCRIMINANT: Int = 3

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

    public companion object : ScaleReader<Type>, ScaleWriter<Type> {
        public override fun read(reader: ScaleCodecReader): Type = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> Transaction.read(reader)
            1 -> Instruction.read(reader)
            2 -> Query.read(reader)
            3 -> Expression.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Type) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Transaction.write(writer, instance as Transaction)
                1 -> Instruction.write(writer, instance as Instruction)
                2 -> Query.write(writer, instance as Query)
                3 -> Expression.write(writer, instance as Expression)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
