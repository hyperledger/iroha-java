//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

/**
 * RejectionReason
 *
 * Generated from 'jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.RejectionReason' enum
 */
public sealed class RejectionReason {
  /**
   * 'Block' variant
   */
  public class Block(
    private val block: BlockRejectionReason
  ) : RejectionReason()

  /**
   * 'Transaction' variant
   */
  public class Transaction(
    private val transaction: TransactionRejectionReason
  ) : RejectionReason()
}
