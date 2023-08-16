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
import kotlin.Unit

/**
 * StringPredicate
 *
 * Generated from 'StringPredicate' enum
 */
public sealed class StringPredicate : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Contains' variant
     */
    public data class Contains(
        public val string: String,
    ) : StringPredicate() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.StringPredicate.Contains>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.StringPredicate.Contains> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.StringPredicate.Contains = try {
                Contains(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.StringPredicate.Contains,
            ): Unit = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'StartsWith' variant
     */
    public data class StartsWith(
        public val string: String,
    ) : StringPredicate() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.StringPredicate.StartsWith>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.StringPredicate.StartsWith> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.StringPredicate.StartsWith = try {
                StartsWith(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.StringPredicate.StartsWith,
            ): Unit = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'EndsWith' variant
     */
    public data class EndsWith(
        public val string: String,
    ) : StringPredicate() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.StringPredicate.EndsWith>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.StringPredicate.EndsWith> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.StringPredicate.EndsWith = try {
                EndsWith(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.StringPredicate.EndsWith,
            ): Unit = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Is' variant
     */
    public data class Is(
        public val string: String,
    ) : StringPredicate() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.StringPredicate.Is>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.StringPredicate.Is> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.StringPredicate.Is = try {
                Is(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.StringPredicate.Is,
            ): Unit = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<StringPredicate>, ScaleWriter<StringPredicate> {
        override fun read(reader: ScaleCodecReader): StringPredicate = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Contains.read(reader)
            1 -> StartsWith.read(reader)
            2 -> EndsWith.read(reader)
            3 -> Is.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: StringPredicate) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Contains.write(writer, instance as Contains)
                1 -> StartsWith.write(writer, instance as StartsWith)
                2 -> EndsWith.write(writer, instance as EndsWith)
                3 -> Is.write(writer, instance as Is)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
