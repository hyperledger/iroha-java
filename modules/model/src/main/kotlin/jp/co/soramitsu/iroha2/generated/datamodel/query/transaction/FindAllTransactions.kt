//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.transaction

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAllTransactions
 *
 * Generated from 'iroha_data_model::query::transaction::FindAllTransactions' tuple structure
 */
public class FindAllTransactions {
    public companion object : ScaleReader<FindAllTransactions>, ScaleWriter<FindAllTransactions> {
        public override fun read(reader: ScaleCodecReader): FindAllTransactions = try {
            FindAllTransactions()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAllTransactions) = try {
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
