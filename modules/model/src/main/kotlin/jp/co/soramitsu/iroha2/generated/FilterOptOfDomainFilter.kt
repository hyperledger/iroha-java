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
 * FilterOptOfDomainFilter
 *
 * Generated from 'FilterOptOfDomainFilter' enum
 */
public sealed class FilterOptOfDomainFilter : ModelEnum {
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
    public class AcceptAll : FilterOptOfDomainFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.FilterOptOfDomainFilter.AcceptAll>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.FilterOptOfDomainFilter.AcceptAll> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.FilterOptOfDomainFilter.AcceptAll = try {
                AcceptAll()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.FilterOptOfDomainFilter.AcceptAll,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.FilterOptOfDomainFilter.AcceptAll,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".FilterOptOfDomainFilter.AcceptAll".hashCode()
        }
    }

    /**
     * 'BySome' variant
     */
    public data class BySome(
        public val domainFilter: DomainFilter,
    ) : FilterOptOfDomainFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.FilterOptOfDomainFilter.BySome>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.FilterOptOfDomainFilter.BySome> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.FilterOptOfDomainFilter.BySome = try {
                BySome(
                    DomainFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.FilterOptOfDomainFilter.BySome,
            ): Unit = try {
                DomainFilter.write(writer, instance.domainFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object :
        ScaleReader<FilterOptOfDomainFilter>,
        ScaleWriter<FilterOptOfDomainFilter> {
        override fun read(reader: ScaleCodecReader): FilterOptOfDomainFilter = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> AcceptAll.read(reader)
            1 -> BySome.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: FilterOptOfDomainFilter) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> AcceptAll.write(writer, instance as AcceptAll)
                1 -> BySome.write(writer, instance as BySome)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
