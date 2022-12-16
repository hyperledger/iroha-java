//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.block.stream

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigInteger
import kotlin.Any
import kotlin.Boolean
import kotlin.Int

/**
 * BlockSubscriberMessage
 *
 * Generated from 'iroha_core::block::stream::BlockSubscriberMessage' enum
 */
public sealed class BlockSubscriberMessage : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is BlockReceived -> BlockReceived.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is BlockReceived -> BlockReceived.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'SubscriptionRequest' variant
     */
    public data class SubscriptionRequest(
        public val u64: BigInteger
    ) : BlockSubscriberMessage() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<SubscriptionRequest>, ScaleWriter<SubscriptionRequest> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): SubscriptionRequest = try {
                SubscriptionRequest(
                    reader.readUint64(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: SubscriptionRequest) = try {
                writer.writeUint64(instance.u64)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'BlockReceived' variant
     */
    public class BlockReceived : BlockSubscriberMessage() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<BlockReceived>, ScaleWriter<BlockReceived> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): BlockReceived = try {
                BlockReceived()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: BlockReceived) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: BlockReceived, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int =
                "core.block.stream.BlockSubscriberMessage.BlockReceived".hashCode()
        }
    }

    public companion object : ScaleReader<BlockSubscriberMessage>, ScaleWriter<BlockSubscriberMessage> {
        public override fun read(reader: ScaleCodecReader): BlockSubscriberMessage = when (
            val
            discriminant = reader.readUByte()
        ) {
            0 -> SubscriptionRequest.read(reader)
            1 -> BlockReceived.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: BlockSubscriberMessage) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> SubscriptionRequest.write(writer, instance as SubscriptionRequest)
                1 -> BlockReceived.write(writer, instance as BlockReceived)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
