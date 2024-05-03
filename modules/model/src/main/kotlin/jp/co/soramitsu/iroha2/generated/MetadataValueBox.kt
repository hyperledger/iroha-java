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
import kotlin.Boolean
import kotlin.ByteArray
import kotlin.Int
import kotlin.Unit
import kotlin.collections.List

/**
 * MetadataValueBox
 *
 * Generated from 'MetadataValueBox' enum
 */
public sealed class MetadataValueBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Bool' variant
     */
    public data class Bool(
        public val bool: Boolean,
    ) : MetadataValueBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.MetadataValueBox.Bool>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.MetadataValueBox.Bool> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.MetadataValueBox.Bool = try {
                Bool(
                    reader.readBoolean(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.MetadataValueBox.Bool,
            ): Unit = try {
                if (instance.bool) { writer.directWrite(1) } else { writer.directWrite(0) }
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'String' variant
     */
    public data class String(
        public val string: kotlin.String,
    ) : MetadataValueBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.MetadataValueBox.String>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.MetadataValueBox.String> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.MetadataValueBox.String = try {
                String(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.MetadataValueBox.String,
            ): Unit = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Name' variant
     */
    public data class Name(
        public val name: jp.co.soramitsu.iroha2.generated.Name,
    ) : MetadataValueBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.MetadataValueBox.Name>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.MetadataValueBox.Name> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.MetadataValueBox.Name = try {
                Name(
                    jp.co.soramitsu.iroha2.generated.Name.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.MetadataValueBox.Name,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Name.write(writer, instance.name)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Bytes' variant
     */
    public data class Bytes(
        public val vec: ByteArray,
    ) : MetadataValueBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.MetadataValueBox.Bytes>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.MetadataValueBox.Bytes> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.MetadataValueBox.Bytes = try {
                Bytes(
                    reader.readByteArray(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.MetadataValueBox.Bytes,
            ): Unit = try {
                writer.writeAsList(instance.vec)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Numeric' variant
     */
    public data class Numeric(
        public val numeric: jp.co.soramitsu.iroha2.generated.Numeric,
    ) : MetadataValueBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.MetadataValueBox.Numeric>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.MetadataValueBox.Numeric> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.MetadataValueBox.Numeric = try {
                Numeric(
                    jp.co.soramitsu.iroha2.generated.Numeric.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.MetadataValueBox.Numeric,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Numeric.write(writer, instance.numeric)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'LimitedMetadata' variant
     */
    public data class LimitedMetadata(
        public val metadata: Metadata,
    ) : MetadataValueBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.MetadataValueBox.LimitedMetadata>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.MetadataValueBox.LimitedMetadata> {
            public const val DISCRIMINANT: Int = 5

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.MetadataValueBox.LimitedMetadata = try {
                LimitedMetadata(
                    Metadata.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.MetadataValueBox.LimitedMetadata,
            ): Unit = try {
                Metadata.write(writer, instance.metadata)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Vec' variant
     */
    public data class Vec(
        public val vec: List<MetadataValueBox>,
    ) : MetadataValueBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.MetadataValueBox.Vec>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.MetadataValueBox.Vec> {
            public const val DISCRIMINANT: Int = 6

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.MetadataValueBox.Vec = try {
                Vec(
                    reader.readVec(reader.readCompactInt()) { MetadataValueBox.read(reader) },
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.MetadataValueBox.Vec,
            ): Unit = try {
                writer.writeCompact(instance.vec.size)
                instance.vec.forEach { value ->
                    MetadataValueBox.write(writer, value)
                }
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<MetadataValueBox>, ScaleWriter<MetadataValueBox> {
        override fun read(reader: ScaleCodecReader): MetadataValueBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Bool.read(reader)
            1 -> String.read(reader)
            2 -> Name.read(reader)
            3 -> Bytes.read(reader)
            4 -> Numeric.read(reader)
            5 -> LimitedMetadata.read(reader)
            6 -> Vec.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: MetadataValueBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Bool.write(writer, instance as Bool)
                1 -> String.write(writer, instance as String)
                2 -> Name.write(writer, instance as Name)
                3 -> Bytes.write(writer, instance as Bytes)
                4 -> Numeric.write(writer, instance as Numeric)
                5 -> LimitedMetadata.write(writer, instance as LimitedMetadata)
                6 -> Vec.write(writer, instance as Vec)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
