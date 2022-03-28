//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * RejectionReason
 *
 * Generated from 'iroha_data_model::transaction::RejectionReason' enum
 */
public sealed class RejectionReason : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Block' variant
     */
    public data class Block(
        public val blockRejectionReason: BlockRejectionReason
    ) : RejectionReason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Block>, ScaleWriter<Block> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Block = try {
                Block(
                    BlockRejectionReason.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Block) = try {
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
        public val transactionRejectionReason: TransactionRejectionReason
    ) : RejectionReason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Transaction>, ScaleWriter<Transaction> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Transaction = try {
                Transaction(
                    TransactionRejectionReason.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Transaction) = try {
                TransactionRejectionReason.write(writer, instance.transactionRejectionReason)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<RejectionReason>, ScaleWriter<RejectionReason> {
        public override fun read(reader: ScaleCodecReader): RejectionReason = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> Block.read(reader)
            1 -> Transaction.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: RejectionReason) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Block.write(writer, instance as Block)
                1 -> Transaction.write(writer, instance as Transaction)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
