//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.codec.reader.CompactBigIntReader
import jp.co.soramitsu.iroha2.codec.writer.CompactULongWriter
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigInteger

/**
 * QueryPayload
 *
 * Generated from 'QueryPayload' regular structure
 */
public data class QueryPayload(
    public val timestampMs: BigInteger,
    public val query: QueryBox,
    public val accountId: AccountId,
    public val filter: GenericPredicateBox<ValuePredicate>
) {
    public companion object : ScaleReader<QueryPayload>, ScaleWriter<QueryPayload> {
        public override fun read(reader: ScaleCodecReader): QueryPayload = try {
            QueryPayload(
                reader.read(CompactBigIntReader()),
                QueryBox.read(reader),
                AccountId.read(reader),
                GenericPredicateBox.read(reader) as GenericPredicateBox<ValuePredicate>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: QueryPayload) = try {
            writer.write(CompactULongWriter(), instance.timestampMs.toLong())
            QueryBox.write(writer, instance.query)
            AccountId.write(writer, instance.accountId)
            GenericPredicateBox.write(writer, instance.filter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
