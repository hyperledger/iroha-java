//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * _VersionedTransactionV1
 *
 * Generated from 'iroha_data_model::transaction::_VersionedTransactionV1' tuple structure
 */
public data class _VersionedTransactionV1(
    public val transaction: Transaction
) {
    public companion object :
        ScaleReader<_VersionedTransactionV1>,
        ScaleWriter<_VersionedTransactionV1> {
        public override fun read(reader: ScaleCodecReader): _VersionedTransactionV1 = try {
            _VersionedTransactionV1(
                Transaction.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: _VersionedTransactionV1) = try {
            Transaction.write(writer, instance.transaction)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
