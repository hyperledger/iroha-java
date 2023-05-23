//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigInteger

/**
 * PaginatedQueryResult
 *
 * Generated from 'PaginatedQueryResult' regular structure
 */
public data class PaginatedQueryResult(
    public val result: QueryResult,
    public val filter: GenericPredicateBox<ValuePredicate>,
    public val pagination: Pagination,
    public val sorting: Sorting,
    public val total: BigInteger
) {
    public companion object : ScaleReader<PaginatedQueryResult>, ScaleWriter<PaginatedQueryResult> {
        public override fun read(reader: ScaleCodecReader): PaginatedQueryResult = try {
            PaginatedQueryResult(
                QueryResult.read(reader),
                GenericPredicateBox.read(reader) as GenericPredicateBox<ValuePredicate>,
                Pagination.read(reader),
                Sorting.read(reader),
                reader.readUint64(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PaginatedQueryResult) = try {
            QueryResult.write(writer, instance.result)
            GenericPredicateBox.write(writer, instance.filter)
            Pagination.write(writer, instance.pagination)
            Sorting.write(writer, instance.sorting)
            writer.writeUint64(instance.total)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
