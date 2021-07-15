//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import java.math.BigInteger
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import kotlin.UInt

/**
 * AssetValue
 *
 * Generated from 'jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue' enum
 */
public sealed class AssetValue {
  /**
   * 'Quantity' variant
   */
  public class Quantity(
    private val quantity: UInt
  ) : AssetValue()

  /**
   * 'BigQuantity' variant
   */
  public class BigQuantity(
    private val bigQuantity: BigInteger
  ) : AssetValue()

  /**
   * 'Store' variant
   */
  public class Store(
    private val store: Metadata
  ) : AssetValue()
}
