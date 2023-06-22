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
        public val triggerOfFilterBoxAndExecutable: TriggerOfFilterBoxAndExecutable
    ) : TriggerBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Raw>, ScaleWriter<Raw> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Raw = try {
                Raw(
                    TriggerOfFilterBoxAndExecutable.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Raw) = try {
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
        public val triggerOfFilterBoxAndOptimizedExecutable: TriggerOfFilterBoxAndOptimizedExecutable
    ) : TriggerBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Optimized>, ScaleWriter<Optimized> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Optimized = try {
                Optimized(
                    TriggerOfFilterBoxAndOptimizedExecutable.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Optimized) = try {
                TriggerOfFilterBoxAndOptimizedExecutable.write(
                    writer,
                    instance.triggerOfFilterBoxAndOptimizedExecutable
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<TriggerBox>, ScaleWriter<TriggerBox> {
        public override fun read(reader: ScaleCodecReader): TriggerBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Raw.read(reader)
            1 -> Optimized.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: TriggerBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Raw.write(writer, instance as Raw)
                1 -> Optimized.write(writer, instance as Optimized)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
