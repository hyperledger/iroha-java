//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * QueryResult
 *
 * Generated from 'QueryResult' regular structure
 */
public data class QueryResult(
    public val `value`: Value
) {
    public companion object : ScaleReader<QueryResult>, ScaleWriter<QueryResult> {
        public override fun read(reader: ScaleCodecReader): QueryResult = try {
            QueryResult(
                Value.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: QueryResult) = try {
            Value.write(writer, instance.`value`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
