//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.smartcontracts.isi.permissions.error

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.core.smartcontracts.isi.error.Mismatch
import jp.co.soramitsu.iroha2.generated.core.smartcontracts.isi.permissions.ValidatorType
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int
import kotlin.String

/**
 * DenialReason
 *
 * Generated from 'iroha_core::smartcontracts::isi::permissions::error::DenialReason' enum
 */
public sealed class DenialReason : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'ValidatorTypeMismatch' variant
     */
    public data class ValidatorTypeMismatch(
        public val mismatch: Mismatch<ValidatorType>
    ) : DenialReason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ValidatorTypeMismatch>, ScaleWriter<ValidatorTypeMismatch> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): ValidatorTypeMismatch = try {
                ValidatorTypeMismatch(
                    Mismatch.read(reader) as Mismatch<ValidatorType>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ValidatorTypeMismatch) = try {
                Mismatch.write(writer, instance.mismatch)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Custom' variant
     */
    public data class Custom(
        public val string: String
    ) : DenialReason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Custom>, ScaleWriter<Custom> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Custom = try {
                Custom(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Custom) = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'NoValidatorsProvided' variant
     */
    public class NoValidatorsProvided : DenialReason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<NoValidatorsProvided>, ScaleWriter<NoValidatorsProvided> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): NoValidatorsProvided = try {
                NoValidatorsProvided()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: NoValidatorsProvided) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<DenialReason>, ScaleWriter<DenialReason> {
        public override fun read(reader: ScaleCodecReader): DenialReason = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> ValidatorTypeMismatch.read(reader)
            1 -> Custom.read(reader)
            2 -> NoValidatorsProvided.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: DenialReason) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> ValidatorTypeMismatch.write(writer, instance as ValidatorTypeMismatch)
                1 -> Custom.write(writer, instance as Custom)
                2 -> NoValidatorsProvided.write(writer, instance as NoValidatorsProvided)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
