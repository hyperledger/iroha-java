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
import kotlin.Unit

/**
 * TriggerBox
 *
 * Generated from 'TriggerBox' enum
 */
public sealed class TriggerBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Raw' variant
     */
    public data class Raw(
        public val triggerOfFilterBoxAndExecutable: TriggerOfFilterBoxAndExecutable,
    ) : TriggerBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TriggerBox.Raw>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TriggerBox.Raw> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TriggerBox.Raw =
                try {
                    Raw(
                        TriggerOfFilterBoxAndExecutable.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TriggerBox.Raw,
            ): Unit = try {
                TriggerOfFilterBoxAndExecutable.write(writer, instance.triggerOfFilterBoxAndExecutable)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Optimized' variant
     */
    public data class Optimized(
        public val triggerOfFilterBoxAndOptimizedExecutable: TriggerOfFilterBoxAndOptimizedExecutable,
    ) : TriggerBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TriggerBox.Optimized>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TriggerBox.Optimized> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TriggerBox.Optimized = try {
                Optimized(
                    TriggerOfFilterBoxAndOptimizedExecutable.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TriggerBox.Optimized,
            ): Unit = try {
                TriggerOfFilterBoxAndOptimizedExecutable.write(
                    writer,
                    instance.triggerOfFilterBoxAndOptimizedExecutable,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<TriggerBox>, ScaleWriter<TriggerBox> {
        override fun read(reader: ScaleCodecReader): TriggerBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Raw.read(reader)
            1 -> Optimized.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: TriggerBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Raw.write(writer, instance as Raw)
                1 -> Optimized.write(writer, instance as Optimized)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
