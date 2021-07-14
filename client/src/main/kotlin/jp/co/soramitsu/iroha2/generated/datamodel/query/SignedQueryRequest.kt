//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query

import java.math.BigInteger
import jp.co.soramitsu.iroha2.generated.crypto.Signature
import kotlin.UInt

/**
 * SignedQueryRequest
 *
 * Generated from 'iroha_data_model::query::SignedQueryRequest' regular structure
 */
public class SignedQueryRequest(
  public val timestampMs: UInt<BigInteger>,
  public val signature: Signature,
  public val query: QueryBox
)
