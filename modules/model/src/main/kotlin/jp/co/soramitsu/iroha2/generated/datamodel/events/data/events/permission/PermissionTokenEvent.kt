//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.permission

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.permission.token.Definition
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * PermissionTokenEvent
 *
 * Generated from 'iroha_data_model::events::data::events::permission::PermissionTokenEvent' enum
 */
public sealed class PermissionTokenEvent : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'DefinitionCreated' variant
     */
    public data class DefinitionCreated(
        public val definition: Definition
    ) : PermissionTokenEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<DefinitionCreated>, ScaleWriter<DefinitionCreated> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): DefinitionCreated = try {
                DefinitionCreated(
                    Definition.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: DefinitionCreated) = try {
                Definition.write(writer, instance.definition)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'DefinitionDeleted' variant
     */
    public data class DefinitionDeleted(
        public val definition: Definition
    ) : PermissionTokenEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<DefinitionDeleted>, ScaleWriter<DefinitionDeleted> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): DefinitionDeleted = try {
                DefinitionDeleted(
                    Definition.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: DefinitionDeleted) = try {
                Definition.write(writer, instance.definition)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<PermissionTokenEvent>, ScaleWriter<PermissionTokenEvent> {
        public override fun read(reader: ScaleCodecReader): PermissionTokenEvent = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> DefinitionCreated.read(reader)
            1 -> DefinitionDeleted.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: PermissionTokenEvent) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> DefinitionCreated.write(writer, instance as DefinitionCreated)
                1 -> DefinitionDeleted.write(writer, instance as DefinitionDeleted)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
