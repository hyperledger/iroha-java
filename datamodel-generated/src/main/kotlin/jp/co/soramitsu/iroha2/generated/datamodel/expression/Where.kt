// Do not change. Autogenerated code
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import kotlin.Pair
import kotlin.String
import kotlin.collections.List

/**
 * Where
 *
 * Matched to regular structure in Rust
 */
public class Where(
  private val expression: EvaluatesTo,
  private val values: List<Pair<String, String>>
) {
  public companion object {
    public const val IROHA_FULL_NAME: String = "iroha_data_model::expression::Where"
  }
}