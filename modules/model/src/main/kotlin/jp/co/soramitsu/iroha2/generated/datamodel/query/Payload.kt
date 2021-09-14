//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import io.emeraldpay.polkaj.scale.reader.CompactBigIntReader
import io.emeraldpay.polkaj.scale.writer.CompactULongWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
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
    public val accountId: Id
) {
    public companion object : ScaleReader<Payload>, ScaleWriter<Payload> {
        public override fun read(reader: ScaleCodecReader): Payload = try {
            Payload(
                reader.read(CompactBigIntReader()),
                QueryBox.read(reader),
                Id.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Payload) = try {
            writer.write(CompactULongWriter(), instance.timestampMs.toLong())
            QueryBox.write(writer, instance.query)
            Id.write(writer, instance.accountId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
