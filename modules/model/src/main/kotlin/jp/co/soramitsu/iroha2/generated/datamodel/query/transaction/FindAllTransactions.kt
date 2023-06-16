//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.transaction

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.Boolean
import kotlin.Int

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

        public fun equals(o1: FindAllTransactions, o2: Any?): Boolean = when (o2) {
            null -> false
            else -> o2::class == o1::class
        }

        public override fun hashCode(): Int =
            "datamodel.query.transaction.FindAllTransactions".hashCode()
    }
}
