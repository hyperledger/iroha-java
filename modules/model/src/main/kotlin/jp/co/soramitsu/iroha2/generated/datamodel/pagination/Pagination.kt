//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.pagination

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Long

/**
 * Pagination
 *
 * Generated from 'iroha_data_model::pagination::Pagination' regular structure
 */
public data class Pagination(
    public val start: Long? = null,
    public val limit: Long? = null
) {
    public companion object : ScaleReader<Pagination>, ScaleWriter<Pagination> {
        public override fun read(reader: ScaleCodecReader): Pagination = try {
            Pagination(
                reader.readNullable(),
                reader.readNullable(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Pagination) = try {
            writer.writeNullable(instance.start)
            writer.writeNullable(instance.limit)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
