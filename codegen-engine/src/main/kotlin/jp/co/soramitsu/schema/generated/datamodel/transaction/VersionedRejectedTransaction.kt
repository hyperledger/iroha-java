// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleReader
import kotlin.Int

/**
 * VersionedRejectedTransaction
 *
 * Generated from 'iroha_data_model::transaction::VersionedRejectedTransaction' enum
 */
public abstract class VersionedRejectedTransaction {
  /**
   * @return Discriminator of variant in enum
   */
  public abstract fun discriminant(): Int

  /**
   * 'V1' variant
   */
  public class V1(
    private val v1: _VersionedRejectedTransactionV1
  ) : VersionedRejectedTransaction() {
    public override fun discriminant(): Int = 1

    public companion object READER : ScaleReader<V1> {
      public override fun read(reader: ScaleCodecReader): V1 {
      }
    }
  }
}
