//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String

/**
 * TransactionLimitError
 *
 * Generated from 'iroha_data_model::transaction::TransactionLimitError' tuple structure
 */
public data class TransactionLimitError(
    public val string: String
) {
    public companion object : ScaleReader<TransactionLimitError>, ScaleWriter<TransactionLimitError> {
        public override fun read(reader: ScaleCodecReader): TransactionLimitError = try {
            TransactionLimitError(
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: TransactionLimitError) = try {
            writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
