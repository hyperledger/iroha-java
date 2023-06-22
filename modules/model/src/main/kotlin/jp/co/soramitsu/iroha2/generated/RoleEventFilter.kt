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
import kotlin.Any
import kotlin.Boolean
import kotlin.Int

/**
 * RoleEventFilter
 *
 * Generated from 'RoleEventFilter' enum
 */
public sealed class RoleEventFilter : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is ByCreated -> ByCreated.equals(this, other)
        is ByDeleted -> ByDeleted.equals(this, other)
        is ByPermissionRemoved -> ByPermissionRemoved.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is ByCreated -> ByCreated.hashCode()
        is ByDeleted -> ByDeleted.hashCode()
        is ByPermissionRemoved -> ByPermissionRemoved.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'ByCreated' variant
     */
    public class ByCreated : RoleEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByCreated>, ScaleWriter<ByCreated> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): ByCreated = try {
                ByCreated()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByCreated) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: ByCreated, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".RoleEventFilter.ByCreated".hashCode()
        }
    }

    /**
     * 'ByDeleted' variant
     */
    public class ByDeleted : RoleEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByDeleted>, ScaleWriter<ByDeleted> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): ByDeleted = try {
                ByDeleted()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByDeleted) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: ByDeleted, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".RoleEventFilter.ByDeleted".hashCode()
        }
    }

    /**
     * 'ByPermissionRemoved' variant
     */
    public class ByPermissionRemoved : RoleEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByPermissionRemoved>, ScaleWriter<ByPermissionRemoved> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): ByPermissionRemoved = try {
                ByPermissionRemoved()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByPermissionRemoved) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: ByPermissionRemoved, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".RoleEventFilter.ByPermissionRemoved".hashCode()
        }
    }

    public companion object : ScaleReader<RoleEventFilter>, ScaleWriter<RoleEventFilter> {
        public override fun read(reader: ScaleCodecReader): RoleEventFilter = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> ByCreated.read(reader)
            1 -> ByDeleted.read(reader)
            2 -> ByPermissionRemoved.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: RoleEventFilter) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> ByCreated.write(writer, instance as ByCreated)
                1 -> ByDeleted.write(writer, instance as ByDeleted)
                2 -> ByPermissionRemoved.write(writer, instance as ByPermissionRemoved)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
