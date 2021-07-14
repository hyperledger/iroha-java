//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import jp.co.soramitsu.iroha2.generated.crypto.Signature
import kotlin.String

/**
 * SignatureVerificationFail
 *
 * Generated from 'iroha_data_model::events::pipeline::SignatureVerificationFail' regular structure
 */
public class SignatureVerificationFail(
  public val signature: Signature,
  public val reason: String
)
