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
import kotlin.Unit

/**
 * QueryExecutionFail
 *
 * Generated from 'QueryExecutionFail' enum
 */
public sealed class QueryExecutionFail : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    override fun equals(other: Any?): Boolean = when (this) {
        is Unauthorized -> Unauthorized.equals(this, other)
        else -> super.equals(other) }

    override fun hashCode(): Int = when (this) {
        is Unauthorized -> Unauthorized.hashCode()
        else -> super.hashCode() }

    /**
     * 'Signature' variant
     */
    public data class Signature(
        public val string: String,
    ) : QueryExecutionFail() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Signature>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Signature> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Signature = try {
                Signature(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Signature,
            ): Unit = try {
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
        public val string: String,
    ) : QueryExecutionFail() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Evaluate>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Evaluate> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Evaluate = try {
                Evaluate(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Evaluate,
            ): Unit = try {
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
        public val findError: FindError,
    ) : QueryExecutionFail() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Find>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Find> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Find = try {
                Find(
                    FindError.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Find,
            ): Unit = try {
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
        public val string: String,
    ) : QueryExecutionFail() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Conversion>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Conversion> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Conversion = try {
                Conversion(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Conversion,
            ): Unit = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Unauthorized' variant
     */
    public class Unauthorized : QueryExecutionFail() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Unauthorized>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Unauthorized> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Unauthorized = try {
                Unauthorized()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Unauthorized,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.QueryExecutionFail.Unauthorized,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".QueryExecutionFail.Unauthorized".hashCode()
        }
    }

    public companion object : ScaleReader<QueryExecutionFail>, ScaleWriter<QueryExecutionFail> {
        override fun read(reader: ScaleCodecReader): QueryExecutionFail = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Signature.read(reader)
            1 -> Evaluate.read(reader)
            2 -> Find.read(reader)
            3 -> Conversion.read(reader)
            4 -> Unauthorized.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: QueryExecutionFail) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Signature.write(writer, instance as Signature)
                1 -> Evaluate.write(writer, instance as Evaluate)
                2 -> Find.write(writer, instance as Find)
                3 -> Conversion.write(writer, instance as Conversion)
                4 -> Unauthorized.write(writer, instance as Unauthorized)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
