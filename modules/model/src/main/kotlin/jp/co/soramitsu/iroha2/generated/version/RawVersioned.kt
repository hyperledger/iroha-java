//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.version

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.ByteArray
import kotlin.Int
import kotlin.String

/**
 * RawVersioned
 *
 * Generated from 'iroha_version::RawVersioned' enum
 */
public sealed class RawVersioned : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Json' variant
     */
    public data class Json(
        public val string: String
    ) : RawVersioned() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Json>, ScaleWriter<Json> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Json = try {
                Json(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Json) = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ScaleBytes' variant
     */
    public data class ScaleBytes(
        public val vec: ByteArray
    ) : RawVersioned() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ScaleBytes>, ScaleWriter<ScaleBytes> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): ScaleBytes = try {
                ScaleBytes(
                    reader.readByteArray(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ScaleBytes) = try {
                writer.writeAsList(instance.vec)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<RawVersioned>, ScaleWriter<RawVersioned> {
        public override fun read(reader: ScaleCodecReader): RawVersioned = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> Json.read(reader)
            1 -> ScaleBytes.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: RawVersioned) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Json.write(writer, instance as Json)
                1 -> ScaleBytes.write(writer, instance as ScaleBytes)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
