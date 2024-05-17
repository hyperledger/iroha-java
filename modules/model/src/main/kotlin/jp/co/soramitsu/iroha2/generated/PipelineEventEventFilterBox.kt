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
 * PipelineEventEventFilterBox
 *
 * Generated from 'PipelineEventEventFilterBox' enum
 */
public sealed class PipelineEventEventFilterBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Transaction' variant
     */
    public data class Transaction(
        public val transactionEventFilter: TransactionEventFilter,
    ) : PipelineEventEventFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.PipelineEventEventFilterBox.Transaction>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.PipelineEventEventFilterBox.Transaction> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.PipelineEventEventFilterBox.Transaction = try {
                Transaction(
                    TransactionEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.PipelineEventEventFilterBox.Transaction,
            ): Unit = try {
                TransactionEventFilter.write(writer, instance.transactionEventFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Block' variant
     */
    public data class Block(
        public val blockEventFilter: BlockEventFilter,
    ) : PipelineEventEventFilterBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.PipelineEventEventFilterBox.Block>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.PipelineEventEventFilterBox.Block> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.PipelineEventEventFilterBox.Block = try {
                Block(
                    BlockEventFilter.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.PipelineEventEventFilterBox.Block,
            ): Unit = try {
                BlockEventFilter.write(writer, instance.blockEventFilter)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<PipelineEventEventFilterBox>, ScaleWriter<PipelineEventEventFilterBox> {
        override fun read(reader: ScaleCodecReader): PipelineEventEventFilterBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Transaction.read(reader)
            1 -> Block.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: PipelineEventEventFilterBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Transaction.write(writer, instance as Transaction)
                1 -> Block.write(writer, instance as Block)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
