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
 * InvalidParameterError
 *
 * Generated from 'InvalidParameterError' enum
 */
public sealed class InvalidParameterError : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    override fun equals(other: Any?): Boolean = when (this) {
        is NameLength -> NameLength.equals(this, other)
        else -> super.equals(other) }

    override fun hashCode(): Int = when (this) {
        is NameLength -> NameLength.hashCode()
        else -> super.hashCode() }

    /**
     * 'Wasm' variant
     */
    public data class Wasm(
        public val string: String,
    ) : InvalidParameterError() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InvalidParameterError.Wasm>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InvalidParameterError.Wasm> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InvalidParameterError.Wasm = try {
                Wasm(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InvalidParameterError.Wasm,
            ): Unit = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'NameLength' variant
     */
    public class NameLength : InvalidParameterError() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InvalidParameterError.NameLength>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InvalidParameterError.NameLength> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InvalidParameterError.NameLength = try {
                NameLength()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InvalidParameterError.NameLength,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.InvalidParameterError.NameLength,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".InvalidParameterError.NameLength".hashCode()
        }
    }

    public companion object : ScaleReader<InvalidParameterError>, ScaleWriter<InvalidParameterError> {
        override fun read(reader: ScaleCodecReader): InvalidParameterError = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Wasm.read(reader)
            1 -> NameLength.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: InvalidParameterError) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Wasm.write(writer, instance as Wasm)
                1 -> NameLength.write(writer, instance as NameLength)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
