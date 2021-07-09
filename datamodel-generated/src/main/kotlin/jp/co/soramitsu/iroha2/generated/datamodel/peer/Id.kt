// Do not change. Autogenerated code
package jp.co.soramitsu.iroha2.generated.datamodel.peer

import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import kotlin.String

/**
 * Id
 *
 * Matched to regular structure in Rust
 */
public class Id(
  private val address: String,
  private val publicKey: PublicKey
) {
  public companion object {
    public const val IROHA_FULL_NAME: String = "iroha_data_model::peer::Id"
  }
}