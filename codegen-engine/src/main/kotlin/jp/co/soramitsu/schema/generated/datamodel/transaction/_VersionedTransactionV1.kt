// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleReader

/**
 * _VersionedTransactionV1
 *
 * Generated from 'iroha_data_model::transaction::_VersionedTransactionV1' tuple structure
 */
public class _VersionedTransactionV1(
  private val transaction: Transaction
) {
  public companion object READER : ScaleReader<_VersionedTransactionV1> {
    public override fun read(reader: ScaleCodecReader): _VersionedTransactionV1 =
        _VersionedTransactionV1(jp.co.soramitsu.schema.generated.datamodel.transaction.Transaction.READER.read(reader))
  }
}
