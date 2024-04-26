//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit

/**
 * QueryPayload
 *
 * Generated from 'QueryPayload' regular structure
 */
public data class QueryPayload(
    public val authority: AccountId,
    public val query: QueryBox,
    public val filter: GenericPredicateBox<ValuePredicate>,
) {
    public companion object : ScaleReader<QueryPayload>, ScaleWriter<QueryPayload> {
        override fun read(reader: ScaleCodecReader): QueryPayload = try {
            QueryPayload(
                AccountId.read(reader),
                QueryBox.read(reader),
                GenericPredicateBox.read(reader) as GenericPredicateBox<ValuePredicate>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: QueryPayload): Unit = try {
            AccountId.write(writer, instance.authority)
            QueryBox.write(writer, instance.query)
            GenericPredicateBox.write(writer, instance.filter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
