//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel

import java.math.BigInteger
import kotlin.UInt

/**
 * Parameter
 *
 * Generated from 'jp.co.soramitsu.iroha2.generated.datamodel.Parameter' enum
 */
public sealed class Parameter {
  /**
   * 'MaximumFaultyPeersAmount' variant
   */
  public class MaximumFaultyPeersAmount(
    private val maximumFaultyPeersAmount: UInt
  ) : Parameter()

  /**
   * 'BlockTime' variant
   */
  public class BlockTime(
    private val blockTime: BigInteger
  ) : Parameter()

  /**
   * 'CommitTime' variant
   */
  public class CommitTime(
    private val commitTime: BigInteger
  ) : Parameter()

  /**
   * 'TransactionReceiptTime' variant
   */
  public class TransactionReceiptTime(
    private val transactionReceiptTime: BigInteger
  ) : Parameter()
}
