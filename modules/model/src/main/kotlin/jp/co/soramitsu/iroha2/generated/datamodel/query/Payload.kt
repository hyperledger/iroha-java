//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.codec.reader.CompactBigIntReader
import jp.co.soramitsu.iroha2.codec.writer.CompactULongWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigInteger

/**
 * Payload
 *
 * Generated from 'iroha_data_model::query::Payload' regular structure
 */
public data class Payload(
    public val timestampMs: BigInteger,
    public val query: QueryBox,
    public val accountId: AccountId
) {
    public companion object : ScaleReader<Payload>, ScaleWriter<Payload> {
        public override fun read(reader: ScaleCodecReader): Payload = try {
            Payload(
                reader.read(CompactBigIntReader()),
                QueryBox.read(reader),
                AccountId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Payload) = try {
            writer.write(CompactULongWriter(), instance.timestampMs.toLong())
            QueryBox.write(writer, instance.query)
            AccountId.write(writer, instance.accountId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
