// Do not change. Autogenerated code
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import kotlin.Int
import kotlin.Pair
import kotlin.String
import kotlin.collections.List

/**
 * Payload
 *
 * Matched to regular structure in Rust
 */
public class Payload(
  private val accountId: Id,
  private val instructions: List<Instruction>,
  private val creationTime: Int,
  private val timeToLiveMs: Int,
  private val metadata: List<Pair<String, String>>
) {
  public companion object {
    public const val IROHA_FULL_NAME: String = "iroha_data_model::transaction::Payload"
  }
}