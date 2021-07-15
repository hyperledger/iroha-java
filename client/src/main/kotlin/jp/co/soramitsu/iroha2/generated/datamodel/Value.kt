//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel

import kotlin.Boolean
import kotlin.UInt
import kotlin.collections.MutableList

/**
 * Value
 *
 * Generated from 'jp.co.soramitsu.iroha2.generated.datamodel.Value' enum
 */
public sealed class Value {
  /**
   * 'U32' variant
   */
  public class U32(
    private val u32: UInt
  ) : Value()

  /**
   * 'Bool' variant
   */
  public class Bool(
    private val bool: Boolean
  ) : Value()

  /**
   * 'String' variant
   */
  public class String(
    private val string: kotlin.String
  ) : Value()

  /**
   * 'Vec' variant
   */
  public class Vec(
    private val vec: MutableList<Value>
  ) : Value()

  /**
   * 'Id' variant
   */
  public class Id(
    private val id: IdBox
  ) : Value()

  /**
   * 'Identifiable' variant
   */
  public class Identifiable(
    private val identifiable: IdentifiableBox
  ) : Value()

  /**
   * 'PublicKey' variant
   */
  public class PublicKey(
    private val publicKey: jp.co.soramitsu.iroha2.generated.crypto.PublicKey
  ) : Value()

  /**
   * 'Parameter' variant
   */
  public class Parameter(
    private val parameter: jp.co.soramitsu.iroha2.generated.datamodel.Parameter
  ) : Value()

  /**
   * 'SignatureCheckCondition' variant
   */
  public class SignatureCheckCondition(
    private val signatureCheckCondition:
        jp.co.soramitsu.iroha2.generated.datamodel.account.SignatureCheckCondition
  ) : Value()

  /**
   * 'TransactionValue' variant
   */
  public class TransactionValue(
    private val transactionValue:
        jp.co.soramitsu.iroha2.generated.datamodel.transaction.TransactionValue
  ) : Value()

  /**
   * 'PermissionToken' variant
   */
  public class PermissionToken(
    private val permissionToken:
        jp.co.soramitsu.iroha2.generated.datamodel.permissions.PermissionToken
  ) : Value()
}
