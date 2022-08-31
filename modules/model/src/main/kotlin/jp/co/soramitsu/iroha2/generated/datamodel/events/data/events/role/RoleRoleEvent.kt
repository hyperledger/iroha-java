//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.role

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.role.RoleId
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * RoleRoleEvent
 *
 * Generated from 'iroha_data_model::events::data::events::role::RoleRoleEvent' enum
 */
public sealed class RoleRoleEvent : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Created' variant
     */
    public data class Created(
        public val roleId: RoleId
    ) : RoleRoleEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Created>, ScaleWriter<Created> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Created = try {
                Created(
                    RoleId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Created) = try {
                RoleId.write(writer, instance.roleId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Deleted' variant
     */
    public data class Deleted(
        public val roleId: RoleId
    ) : RoleRoleEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Deleted>, ScaleWriter<Deleted> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Deleted = try {
                Deleted(
                    RoleId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Deleted) = try {
                RoleId.write(writer, instance.roleId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'PermissionRemoved' variant
     */
    public data class PermissionRemoved(
        public val permissionRemoved:  
            jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.role.PermissionRemoved
    ) : RoleRoleEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<PermissionRemoved>, ScaleWriter<PermissionRemoved> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): PermissionRemoved = try {
                PermissionRemoved(
                    jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.role.PermissionRemoved.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: PermissionRemoved) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.role.PermissionRemoved.write(
                    writer,
                    instance.permissionRemoved
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<RoleRoleEvent>, ScaleWriter<RoleRoleEvent> {
        public override fun read(reader: ScaleCodecReader): RoleRoleEvent = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> Created.read(reader)
            1 -> Deleted.read(reader)
            2 -> PermissionRemoved.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: RoleRoleEvent) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Created.write(writer, instance as Created)
                1 -> Deleted.write(writer, instance as Deleted)
                2 -> PermissionRemoved.write(writer, instance as PermissionRemoved)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
