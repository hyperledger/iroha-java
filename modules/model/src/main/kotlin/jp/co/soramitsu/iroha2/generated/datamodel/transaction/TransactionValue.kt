//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * TransactionValue
 *
 * Generated from 'iroha_data_model::transaction::TransactionValue' enum
 */
public sealed class TransactionValue : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Transaction' variant
     */
    public data class Transaction(
        public val versionedTransaction: VersionedTransaction
    ) : TransactionValue() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Transaction>, ScaleWriter<Transaction> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Transaction = try {
                Transaction(
                    VersionedTransaction.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Transaction) = try {
                VersionedTransaction.write(writer, instance.versionedTransaction)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'RejectedTransaction' variant
     */
    public data class RejectedTransaction(
        public val versionedRejectedTransaction: VersionedRejectedTransaction
    ) : TransactionValue() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<RejectedTransaction>, ScaleWriter<RejectedTransaction> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): RejectedTransaction = try {
                RejectedTransaction(
                    VersionedRejectedTransaction.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: RejectedTransaction) = try {
                VersionedRejectedTransaction.write(writer, instance.versionedRejectedTransaction)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<TransactionValue>, ScaleWriter<TransactionValue> {
        public override fun read(reader: ScaleCodecReader): TransactionValue = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Transaction.read(reader)
            1 -> RejectedTransaction.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: TransactionValue) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Transaction.write(writer, instance as Transaction)
                1 -> RejectedTransaction.write(writer, instance as RejectedTransaction)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
