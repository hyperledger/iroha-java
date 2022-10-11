//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.wrapException

/**
 * TransactionQueryResult
 *
 * Generated from 'iroha_data_model::transaction::TransactionQueryResult' regular structure
 */
public data class TransactionQueryResult(
    public val txValue: TransactionValue,
    public val blockHash: Hash
) {
    public companion object : ScaleReader<TransactionQueryResult>, ScaleWriter<TransactionQueryResult> {
        public override fun read(reader: ScaleCodecReader): TransactionQueryResult = try {
            TransactionQueryResult(
                TransactionValue.read(reader),
                Hash.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: TransactionQueryResult) = try {
            TransactionValue.write(writer, instance.txValue)
            Hash.write(writer, instance.blockHash)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
