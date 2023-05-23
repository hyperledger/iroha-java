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
 * ValidatorMode
 *
 * Generated from 'ValidatorMode' enum
 */
public sealed class ValidatorMode : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Path' variant
     */
    public data class Path(
        public val validatorPath: ValidatorPath
    ) : ValidatorMode() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Path>, ScaleWriter<Path> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Path = try {
                Path(
                    ValidatorPath.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Path) = try {
                ValidatorPath.write(writer, instance.validatorPath)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Inline' variant
     */
    public data class Inline(
        public val validator: Validator
    ) : ValidatorMode() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Inline>, ScaleWriter<Inline> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Inline = try {
                Inline(
                    Validator.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Inline) = try {
                Validator.write(writer, instance.validator)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<ValidatorMode>, ScaleWriter<ValidatorMode> {
        public override fun read(reader: ScaleCodecReader): ValidatorMode = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Path.read(reader)
            1 -> Inline.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: ValidatorMode) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Path.write(writer, instance as Path)
                1 -> Inline.write(writer, instance as Inline)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
