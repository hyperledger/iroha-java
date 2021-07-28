//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Int
import kotlin.Unit

/**
 * RejectionReason
 *
 * Generated from 'iroha_data_model::events::pipeline::RejectionReason' enum
 */
public sealed class RejectionReason {
  /**
   * @return Discriminator of variant in enum
   */
  public abstract fun discriminant(): Int

  /**
   * 'Block' variant
   */
  public class Block(
    private val blockRejectionReason: BlockRejectionReason
  ) : RejectionReason() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Block>, ScaleWriter<Block> {
      public const val DISCRIMINANT: Int = 0

      public override fun read(reader: ScaleCodecReader): Block = Block(
        BlockRejectionReason.read(reader),
      )

      public override fun write(writer: ScaleCodecWriter, instance: Block): Unit {

      }
    }
  }

  /**
   * 'Transaction' variant
   */
  public class Transaction(
    private val transactionRejectionReason: TransactionRejectionReason
  ) : RejectionReason() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Transaction>, ScaleWriter<Transaction> {
      public const val DISCRIMINANT: Int = 1

      public override fun read(reader: ScaleCodecReader): Transaction = Transaction(
        TransactionRejectionReason.read(reader),
      )

      public override fun write(writer: ScaleCodecWriter, instance: Transaction): Unit {

      }
    }
  }

  public companion object : ScaleReader<RejectionReason>, ScaleWriter<RejectionReason> {
    public override fun read(reader: ScaleCodecReader): RejectionReason = when(val discriminant =
        reader.readUByte()) {
    	0 -> Block.read(reader)
    	1 -> Transaction.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}

    public override fun write(writer: ScaleCodecWriter, instance: RejectionReason): Unit {
      writer.directWrite(instance.discriminant())
      when(val discriminant = instance.discriminant()) {
      	0 -> Block.write(writer, instance as Block)
      	1 -> Transaction.write(writer, instance as Transaction)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}
    }
  }
}
