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
import kotlin.Int
import kotlin.Unit

/**
 * PipelineRejectionReason
 *
 * Generated from 'PipelineRejectionReason' enum
 */
public sealed class PipelineRejectionReason : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Block' variant
     */
    public data class Block(
        public val blockRejectionReason: BlockRejectionReason,
    ) : PipelineRejectionReason() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.PipelineRejectionReason.Block>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.PipelineRejectionReason.Block> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.PipelineRejectionReason.Block = try {
                Block(
                    BlockRejectionReason.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.PipelineRejectionReason.Block,
            ): Unit = try {
                BlockRejectionReason.write(writer, instance.blockRejectionReason)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Transaction' variant
     */
    public data class Transaction(
        public val transactionRejectionReason: TransactionRejectionReason,
    ) : PipelineRejectionReason() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.PipelineRejectionReason.Transaction>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.PipelineRejectionReason.Transaction> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.PipelineRejectionReason.Transaction = try {
                Transaction(
                    TransactionRejectionReason.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.PipelineRejectionReason.Transaction,
            ): Unit =
                try {
                    TransactionRejectionReason.write(writer, instance.transactionRejectionReason)
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }
        }
    }

    public companion object :
        ScaleReader<PipelineRejectionReason>,
        ScaleWriter<PipelineRejectionReason> {
        override fun read(reader: ScaleCodecReader): PipelineRejectionReason = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Block.read(reader)
            1 -> Transaction.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: PipelineRejectionReason) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Block.write(writer, instance as Block)
                1 -> Transaction.write(writer, instance as Transaction)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
