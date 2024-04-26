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
 * ExecutorMode
 *
 * Generated from 'ExecutorMode' enum
 */
public sealed class ExecutorMode : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Path' variant
     */
    public data class Path(
        public val string: String,
    ) : ExecutorMode() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ExecutorMode.Path>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ExecutorMode.Path> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ExecutorMode.Path = try {
                Path(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ExecutorMode.Path,
            ): Unit = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Inline' variant
     */
    public data class Inline(
        public val executor: Executor,
    ) : ExecutorMode() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ExecutorMode.Inline>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ExecutorMode.Inline> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ExecutorMode.Inline = try {
                Inline(
                    Executor.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ExecutorMode.Inline,
            ): Unit = try {
                Executor.write(writer, instance.executor)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<ExecutorMode>, ScaleWriter<ExecutorMode> {
        override fun read(reader: ScaleCodecReader): ExecutorMode = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Path.read(reader)
            1 -> Inline.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: ExecutorMode) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Path.write(writer, instance as Path)
                1 -> Inline.write(writer, instance as Inline)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
