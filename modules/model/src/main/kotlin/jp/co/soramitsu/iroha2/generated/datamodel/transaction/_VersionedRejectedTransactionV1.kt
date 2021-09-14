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
 * _VersionedRejectedTransactionV1
 *
 * Generated from 'iroha_data_model::transaction::_VersionedRejectedTransactionV1' tuple structure
 */
public data class _VersionedRejectedTransactionV1(
    public val rejectedTransaction: RejectedTransaction
) {
    public companion object :
        ScaleReader<_VersionedRejectedTransactionV1>,
        ScaleWriter<_VersionedRejectedTransactionV1> {
        public override fun read(reader: ScaleCodecReader): _VersionedRejectedTransactionV1 = try {
            _VersionedRejectedTransactionV1(
                RejectedTransaction.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: _VersionedRejectedTransactionV1) =
            try {
                RejectedTransaction.write(writer, instance.rejectedTransaction)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
