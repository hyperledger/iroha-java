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
 * FilterOptOfOriginFilterOfRoleEvent
 *
 * Generated from 'FilterOptOfOriginFilterOfRoleEvent' enum
 */
public sealed class FilterOptOfOriginFilterOfRoleEvent : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is AcceptAll -> AcceptAll.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is AcceptAll -> AcceptAll.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'AcceptAll' variant
     */
    public class AcceptAll : FilterOptOfOriginFilterOfRoleEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AcceptAll>, ScaleWriter<AcceptAll> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): AcceptAll = try {
                AcceptAll()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AcceptAll) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: AcceptAll, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int =
                ".FilterOptOfOriginFilterOfRoleEvent.AcceptAll".hashCode()
        }
    }

    /**
     * 'BySome' variant
     */
    public data class BySome(
        public val originFilterOfRoleEvent: OriginFilterOfRoleEvent
    ) : FilterOptOfOriginFilterOfRoleEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<BySome>, ScaleWriter<BySome> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): BySome = try {
                BySome(
                    OriginFilterOfRoleEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: BySome) = try {
                OriginFilterOfRoleEvent.write(writer, instance.originFilterOfRoleEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object :
        ScaleReader<FilterOptOfOriginFilterOfRoleEvent>,
        ScaleWriter<FilterOptOfOriginFilterOfRoleEvent> {
        public override fun read(reader: ScaleCodecReader): FilterOptOfOriginFilterOfRoleEvent =
            when (val discriminant = reader.readUByte()) {
                0 -> AcceptAll.read(reader)
                1 -> BySome.read(reader)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }

        public override fun write(
            writer: ScaleCodecWriter,
            instance: FilterOptOfOriginFilterOfRoleEvent
        ) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> AcceptAll.write(writer, instance as AcceptAll)
                1 -> BySome.write(writer, instance as BySome)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
