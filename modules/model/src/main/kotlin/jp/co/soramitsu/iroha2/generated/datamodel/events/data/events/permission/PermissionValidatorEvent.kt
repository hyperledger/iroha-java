//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.permission

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.permission.validator.ValidatorId
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * PermissionValidatorEvent
 *
 * Generated from 'iroha_data_model::events::data::events::permission::PermissionValidatorEvent'
 * enum
 */
public sealed class PermissionValidatorEvent : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Added' variant
     */
    public data class Added(
        public val validatorId: ValidatorId
    ) : PermissionValidatorEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Added>, ScaleWriter<Added> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Added = try {
                Added(
                    ValidatorId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Added) = try {
                ValidatorId.write(writer, instance.validatorId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Removed' variant
     */
    public data class Removed(
        public val validatorId: ValidatorId
    ) : PermissionValidatorEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Removed>, ScaleWriter<Removed> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Removed = try {
                Removed(
                    ValidatorId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Removed) = try {
                ValidatorId.write(writer, instance.validatorId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object :
        ScaleReader<PermissionValidatorEvent>,
        ScaleWriter<PermissionValidatorEvent> {
        public override fun read(reader: ScaleCodecReader): PermissionValidatorEvent = when (
            val
            discriminant = reader.readUByte()
        ) {
            0 -> Added.read(reader)
            1 -> Removed.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: PermissionValidatorEvent) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Added.write(writer, instance as Added)
                1 -> Removed.write(writer, instance as Removed)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
