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
import kotlin.Long
import kotlin.String

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

    /**
     * 'Wasm' variant
     */
    public data class Wasm(
        public val string: String
    ) : InvalidParameterError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Wasm>, ScaleWriter<Wasm> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Wasm = try {
                Wasm(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Wasm) = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'NameLength' variant
     */
    public data class NameLength(
        public val rangeInclusive: RangeInclusive<Long>
    ) : InvalidParameterError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<NameLength>, ScaleWriter<NameLength> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): NameLength = try {
                NameLength(
                    RangeInclusive.read(reader) as RangeInclusive<Long>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: NameLength) = try {
                RangeInclusive.write(writer, instance.rangeInclusive)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<InvalidParameterError>, ScaleWriter<InvalidParameterError> {
        public override fun read(reader: ScaleCodecReader): InvalidParameterError = when (
            val
            discriminant = reader.readUByte()
        ) {
            0 -> Wasm.read(reader)
            1 -> NameLength.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: InvalidParameterError) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Wasm.write(writer, instance as Wasm)
                1 -> NameLength.write(writer, instance as NameLength)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
