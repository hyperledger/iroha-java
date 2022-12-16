//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.peer

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.peer.PeerId
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * PeerEvent
 *
 * Generated from 'iroha_data_model::events::data::events::peer::PeerEvent' enum
 */
public sealed class PeerEvent : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Added' variant
     */
    public data class Added(
        public val peerId: PeerId
    ) : PeerEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Added>, ScaleWriter<Added> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Added = try {
                Added(
                    PeerId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Added) = try {
                PeerId.write(writer, instance.peerId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Removed' variant
     */
    public data class Removed(
        public val peerId: PeerId
    ) : PeerEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Removed>, ScaleWriter<Removed> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Removed = try {
                Removed(
                    PeerId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Removed) = try {
                PeerId.write(writer, instance.peerId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<PeerEvent>, ScaleWriter<PeerEvent> {
        public override fun read(reader: ScaleCodecReader): PeerEvent = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Added.read(reader)
            1 -> Removed.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: PeerEvent) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Added.write(writer, instance as Added)
                1 -> Removed.write(writer, instance as Removed)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
