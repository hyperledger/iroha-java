//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Int
import kotlin.Unit

/**
 * TransactionValue
 *
 * Generated from 'iroha_data_model::transaction::TransactionValue' enum
 */
public sealed class TransactionValue {
  /**
   * @return Discriminator of variant in enum
   */
  public abstract fun discriminant(): Int

  /**
   * 'Transaction' variant
   */
  public class Transaction(
    public val versionedTransaction: VersionedTransaction
  ) : TransactionValue() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Transaction>, ScaleWriter<Transaction> {
      public const val DISCRIMINANT: Int = 0

      public override fun read(reader: ScaleCodecReader): Transaction = Transaction(
        VersionedTransaction.read(reader) as VersionedTransaction,
      )

      public override fun write(writer: ScaleCodecWriter, instance: Transaction): Unit {
          VersionedTransaction.write(writer, instance.versionedTransaction)
      }
    }
  }

  /**
   * 'RejectedTransaction' variant
   */
  public class RejectedTransaction(
    public val versionedRejectedTransaction: VersionedRejectedTransaction
  ) : TransactionValue() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<RejectedTransaction>, ScaleWriter<RejectedTransaction> {
      public const val DISCRIMINANT: Int = 1

      public override fun read(reader: ScaleCodecReader): RejectedTransaction = RejectedTransaction(
        VersionedRejectedTransaction.read(reader) as VersionedRejectedTransaction,
      )

      public override fun write(writer: ScaleCodecWriter, instance: RejectedTransaction): Unit {
          VersionedRejectedTransaction.write(writer, instance.versionedRejectedTransaction)
      }
    }
  }

  public companion object : ScaleReader<TransactionValue>, ScaleWriter<TransactionValue> {
    public override fun read(reader: ScaleCodecReader): TransactionValue = when(val discriminant =
        reader.readUByte()) {
    	0 -> Transaction.read(reader)
    	1 -> RejectedTransaction.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}

    public override fun write(writer: ScaleCodecWriter, instance: TransactionValue): Unit {
      writer.directWrite(instance.discriminant())
      when(val discriminant = instance.discriminant()) {
      	0 -> Transaction.write(writer, instance as Transaction)
      	1 -> RejectedTransaction.write(writer, instance as RejectedTransaction)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}
    }
  }
}
