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
import kotlin.Unit

/**
 * FilterOptOfRoleEventFilter
 *
 * Generated from 'FilterOptOfRoleEventFilter' enum
 */
public sealed class FilterOptOfRoleEventFilter : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    override fun equals(other: Any?): Boolean = when (this) {
        is AcceptAll -> AcceptAll.equals(this, other)
        else -> super.equals(other) }

    override fun hashCode(): Int = when (this) {
        is AcceptAll -> AcceptAll.hashCode()
        else -> super.hashCode() }

    /**
     * 'AcceptAll' variant
     */
    public class AcceptAll : FilterOptOfRoleEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.FilterOptOfRoleEventFilter.AcceptAll>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.FilterOptOfRoleEventFilter.AcceptAll> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.FilterOptOfRoleEventFilter.AcceptAll = try {
                AcceptAll()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.FilterOptOfRoleEventFilter.AcceptAll,
            ): Unit =
                try {
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.FilterOptOfRoleEventFilter.AcceptAll,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".FilterOptOfRoleEventFilter.AcceptAll".hashCode()
        }
    }

    /**
     * 'BySome' variant
     */
    public data class BySome(
        public val roleEventFilter: RoleEventFilter,
    ) : FilterOptOfRoleEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.FilterOptOfRoleEventFilter.BySome>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.FilterOptOfRoleEventFilter.BySome> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.FilterOptOfRoleEventFilter.BySome = try {
                BySome(
                    RoleEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.FilterOptOfRoleEventFilter.BySome,
            ): Unit = try {
                RoleEventFilter.write(writer, instance.roleEventFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object :
        ScaleReader<FilterOptOfRoleEventFilter>,
        ScaleWriter<FilterOptOfRoleEventFilter> {
        override fun read(reader: ScaleCodecReader): FilterOptOfRoleEventFilter = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> AcceptAll.read(reader)
            1 -> BySome.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: FilterOptOfRoleEventFilter) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> AcceptAll.write(writer, instance as AcceptAll)
                1 -> BySome.write(writer, instance as BySome)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
