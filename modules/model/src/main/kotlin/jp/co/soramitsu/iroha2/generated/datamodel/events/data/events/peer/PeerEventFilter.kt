//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.peer

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
 * PeerEventFilter
 *
 * Generated from 'iroha_data_model::events::data::events::peer::PeerEventFilter' enum
 */
public sealed class PeerEventFilter : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is ByAdded -> ByAdded.equals(this, other)
        is ByRemoved -> ByRemoved.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is ByAdded -> ByAdded.hashCode()
        is ByRemoved -> ByRemoved.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'ByAdded' variant
     */
    public class ByAdded : PeerEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByAdded>, ScaleWriter<ByAdded> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): ByAdded = try {
                ByAdded()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByAdded) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: ByAdded, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int =
                "datamodel.events.data.events.peer.PeerEventFilter.ByAdded".hashCode()
        }
    }

    /**
     * 'ByRemoved' variant
     */
    public class ByRemoved : PeerEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByRemoved>, ScaleWriter<ByRemoved> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): ByRemoved = try {
                ByRemoved()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByRemoved) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: ByRemoved, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int =
                "datamodel.events.data.events.peer.PeerEventFilter.ByRemoved".hashCode()
        }
    }

    public companion object : ScaleReader<PeerEventFilter>, ScaleWriter<PeerEventFilter> {
        public override fun read(reader: ScaleCodecReader): PeerEventFilter = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> ByAdded.read(reader)
            1 -> ByRemoved.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: PeerEventFilter) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> ByAdded.write(writer, instance as ByAdded)
                1 -> ByRemoved.write(writer, instance as ByRemoved)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
