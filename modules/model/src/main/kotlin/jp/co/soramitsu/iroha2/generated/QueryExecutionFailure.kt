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
 * QueryExecutionFailure
 *
 * Generated from 'QueryExecutionFailure' enum
 */
public sealed class QueryExecutionFailure : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is Unauthorized -> Unauthorized.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is Unauthorized -> Unauthorized.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'Signature' variant
     */
    public data class Signature(
        public val string: String
    ) : QueryExecutionFailure() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Signature>, ScaleWriter<Signature> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Signature = try {
                Signature(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Signature) = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Evaluate' variant
     */
    public data class Evaluate(
        public val string: String
    ) : QueryExecutionFailure() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Evaluate>, ScaleWriter<Evaluate> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Evaluate = try {
                Evaluate(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Evaluate) = try {
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
    ) : QueryExecutionFailure() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Find>, ScaleWriter<Find> {
            public const val DISCRIMINANT: Int = 2

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
     * 'Conversion' variant
     */
    public data class Conversion(
        public val string: String
    ) : QueryExecutionFailure() {
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

    /**
     * 'Unauthorized' variant
     */
    public class Unauthorized : QueryExecutionFailure() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Unauthorized>, ScaleWriter<Unauthorized> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): Unauthorized = try {
                Unauthorized()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Unauthorized) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Unauthorized, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".QueryExecutionFailure.Unauthorized".hashCode()
        }
    }

    public companion object : ScaleReader<QueryExecutionFailure>, ScaleWriter<QueryExecutionFailure> {
        public override fun read(reader: ScaleCodecReader): QueryExecutionFailure = when (
            val
            discriminant = reader.readUByte()
        ) {
            0 -> Signature.read(reader)
            1 -> Evaluate.read(reader)
            2 -> Find.read(reader)
            3 -> Conversion.read(reader)
            4 -> Unauthorized.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: QueryExecutionFailure) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Signature.write(writer, instance as Signature)
                1 -> Evaluate.write(writer, instance as Evaluate)
                2 -> Find.write(writer, instance as Find)
                3 -> Conversion.write(writer, instance as Conversion)
                4 -> Unauthorized.write(writer, instance as Unauthorized)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
