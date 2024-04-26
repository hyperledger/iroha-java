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
 * PeerEventFilter
 *
 * Generated from 'PeerEventFilter' enum
 */
public sealed class PeerEventFilter : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    override fun equals(other: Any?): Boolean = when (this) {
        is ByAdded -> ByAdded.equals(this, other)
        is ByRemoved -> ByRemoved.equals(this, other)
        else -> super.equals(other) }

    override fun hashCode(): Int = when (this) {
        is ByAdded -> ByAdded.hashCode()
        is ByRemoved -> ByRemoved.hashCode()
        else -> super.hashCode() }

    /**
     * 'ByAdded' variant
     */
    public class ByAdded : PeerEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.PeerEventFilter.ByAdded>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.PeerEventFilter.ByAdded> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.PeerEventFilter.ByAdded = try {
                ByAdded()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.PeerEventFilter.ByAdded,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: jp.co.soramitsu.iroha2.generated.PeerEventFilter.ByAdded, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".PeerEventFilter.ByAdded".hashCode()
        }
    }

    /**
     * 'ByRemoved' variant
     */
    public class ByRemoved : PeerEventFilter() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.PeerEventFilter.ByRemoved>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.PeerEventFilter.ByRemoved> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.PeerEventFilter.ByRemoved = try {
                ByRemoved()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.PeerEventFilter.ByRemoved,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: jp.co.soramitsu.iroha2.generated.PeerEventFilter.ByRemoved, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".PeerEventFilter.ByRemoved".hashCode()
        }
    }

    public companion object : ScaleReader<PeerEventFilter>, ScaleWriter<PeerEventFilter> {
        override fun read(reader: ScaleCodecReader): PeerEventFilter = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> ByAdded.read(reader)
            1 -> ByRemoved.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: PeerEventFilter) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> ByAdded.write(writer, instance as ByAdded)
                1 -> ByRemoved.write(writer, instance as ByRemoved)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
