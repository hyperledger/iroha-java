// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.events.pipeline

import kotlin.Int

/**
 * BlockRejectionReason
 *
 * Generated from 'iroha_data_model::events::pipeline::BlockRejectionReason' enum
 */
public abstract class BlockRejectionReason {
  /**
   * @return Discriminator of variant in enum
   */
  public abstract fun discriminant(): Int

  /**
   * 'ConsensusBlockRejection' variant
   */
  public class ConsensusBlockRejection : BlockRejectionReason() {
    public override fun discriminant(): Int = 0
  }
}
