//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * Reason
 *
 * Generated from 'iroha_core::sumeragi::view_change::Reason' enum
 */
public sealed class Reason : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'CommitTimeout' variant
     */
    public data class CommitTimeout(
        public val commitTimeout:  
            jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange.CommitTimeout
    ) : Reason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<CommitTimeout>, ScaleWriter<CommitTimeout> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): CommitTimeout = try {
                CommitTimeout(
                    jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange.CommitTimeout.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: CommitTimeout) = try {
                jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange.CommitTimeout.write(
                    writer,
                    instance.commitTimeout
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'NoTransactionReceiptReceived' variant
     */
    public data class NoTransactionReceiptReceived(
        public val noTransactionReceiptReceived:  
            jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange.NoTransactionReceiptReceived
    ) : Reason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<NoTransactionReceiptReceived>,
            ScaleWriter<NoTransactionReceiptReceived> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): NoTransactionReceiptReceived = try {
                NoTransactionReceiptReceived(
                    jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange.NoTransactionReceiptReceived.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: NoTransactionReceiptReceived) =
                try {
                    jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange.NoTransactionReceiptReceived.write(
                        writer,
                        instance.noTransactionReceiptReceived
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }
        }
    }

    /**
     * 'BlockCreationTimeout' variant
     */
    public data class BlockCreationTimeout(
        public val blockCreationTimeout:  
            jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange.BlockCreationTimeout
    ) : Reason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<BlockCreationTimeout>, ScaleWriter<BlockCreationTimeout> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): BlockCreationTimeout = try {
                BlockCreationTimeout(
                    jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange.BlockCreationTimeout.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: BlockCreationTimeout) = try {
                jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange.BlockCreationTimeout.write(
                    writer,
                    instance.blockCreationTimeout
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Reason>, ScaleWriter<Reason> {
        public override fun read(reader: ScaleCodecReader): Reason = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> CommitTimeout.read(reader)
            1 -> NoTransactionReceiptReceived.read(reader)
            2 -> BlockCreationTimeout.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Reason) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> CommitTimeout.write(writer, instance as CommitTimeout)
                1 -> NoTransactionReceiptReceived.write(writer, instance as NoTransactionReceiptReceived)
                2 -> BlockCreationTimeout.write(writer, instance as BlockCreationTimeout)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
