//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.block.stream

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.core.block.VersionedCommittedBlock
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.Boolean
import kotlin.Int

/**
 * BlockPublisherMessage
 *
 * Generated from 'iroha_core::block::stream::BlockPublisherMessage' enum
 */
public sealed class BlockPublisherMessage : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is SubscriptionAccepted -> SubscriptionAccepted.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is SubscriptionAccepted -> SubscriptionAccepted.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'SubscriptionAccepted' variant
     */
    public class SubscriptionAccepted : BlockPublisherMessage() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<SubscriptionAccepted>, ScaleWriter<SubscriptionAccepted> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): SubscriptionAccepted = try {
                SubscriptionAccepted()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: SubscriptionAccepted) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: SubscriptionAccepted, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = 1
        }
    }

    /**
     * 'Block' variant
     */
    public data class Block(
        public val versionedCommittedBlock: VersionedCommittedBlock
    ) : BlockPublisherMessage() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Block>, ScaleWriter<Block> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Block = try {
                Block(
                    VersionedCommittedBlock.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Block) = try {
                VersionedCommittedBlock.write(writer, instance.versionedCommittedBlock)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<BlockPublisherMessage>, ScaleWriter<BlockPublisherMessage> {
        public override fun read(reader: ScaleCodecReader): BlockPublisherMessage = when (
            val
            discriminant = reader.readUByte()
        ) {
            0 -> SubscriptionAccepted.read(reader)
            1 -> Block.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: BlockPublisherMessage) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> SubscriptionAccepted.write(writer, instance as SubscriptionAccepted)
                1 -> Block.write(writer, instance as Block)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
