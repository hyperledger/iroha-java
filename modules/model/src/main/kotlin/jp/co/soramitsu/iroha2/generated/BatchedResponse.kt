//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.Unit
import kotlin.collections.List

/**
 * BatchedResponse
 *
 * Generated from 'BatchedResponse<Vec<VersionedSignedTransaction>>' regular structure
 */
public data class BatchedResponse<T0>(
    public val batch: List<VersionedSignedTransaction>,
    public val cursor: ForwardCursor,
) {
    public companion object :
        ScaleReader<BatchedResponse<out Any>>,
        ScaleWriter<BatchedResponse<out Any>> {
        override fun read(reader: ScaleCodecReader): BatchedResponse<out Any> = try {
            BatchedResponse(
                reader.readVec(reader.readCompactInt()) { VersionedSignedTransaction.read(reader) },
                ForwardCursor.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: BatchedResponse<out Any>): Unit = try {
            writer.writeCompact(instance.batch.size)
            instance.batch.forEach { value ->
                VersionedSignedTransaction.write(writer, value)
            }
            ForwardCursor.write(writer, instance.cursor)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
