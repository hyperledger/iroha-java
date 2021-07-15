//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

/**
 * TransactionValue
 *
 * Generated from 'jp.co.soramitsu.iroha2.generated.datamodel.transaction.TransactionValue' enum
 */
public sealed class TransactionValue {
  /**
   * 'Transaction' variant
   */
  public class Transaction(
    private val transaction: VersionedTransaction
  ) : TransactionValue()

  /**
   * 'RejectedTransaction' variant
   */
  public class RejectedTransaction(
    private val rejectedTransaction: VersionedRejectedTransaction
  ) : TransactionValue()
}
