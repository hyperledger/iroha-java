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
 * FilterOptOfAssetEventFilter
 *
 * Generated from 'FilterOptOfAssetEventFilter' enum
 */
public sealed class FilterOptOfAssetEventFilter : ModelEnum {
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
    public class AcceptAll : FilterOptOfAssetEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.FilterOptOfAssetEventFilter.AcceptAll>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.FilterOptOfAssetEventFilter.AcceptAll> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.FilterOptOfAssetEventFilter.AcceptAll = try {
                AcceptAll()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.FilterOptOfAssetEventFilter.AcceptAll,
            ): Unit =
                try {
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.FilterOptOfAssetEventFilter.AcceptAll,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".FilterOptOfAssetEventFilter.AcceptAll".hashCode()
        }
    }

    /**
     * 'BySome' variant
     */
    public data class BySome(
        public val assetEventFilter: AssetEventFilter,
    ) : FilterOptOfAssetEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.FilterOptOfAssetEventFilter.BySome>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.FilterOptOfAssetEventFilter.BySome> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.FilterOptOfAssetEventFilter.BySome = try {
                BySome(
                    AssetEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.FilterOptOfAssetEventFilter.BySome,
            ): Unit = try {
                AssetEventFilter.write(writer, instance.assetEventFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object :
        ScaleReader<FilterOptOfAssetEventFilter>,
        ScaleWriter<FilterOptOfAssetEventFilter> {
        override fun read(reader: ScaleCodecReader): FilterOptOfAssetEventFilter = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> AcceptAll.read(reader)
            1 -> BySome.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: FilterOptOfAssetEventFilter) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> AcceptAll.write(writer, instance as AcceptAll)
                1 -> BySome.write(writer, instance as BySome)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
