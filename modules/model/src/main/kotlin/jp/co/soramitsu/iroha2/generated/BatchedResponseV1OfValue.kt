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
 * BatchedResponseV1OfValue
 *
 * Generated from 'BatchedResponseV1OfValue' regular structure
 */
public data class BatchedResponseV1OfValue(
    public val batch: Value,
    public val cursor: ForwardCursor,
) {
    public companion object :
        ScaleReader<BatchedResponseV1OfValue>,
        ScaleWriter<BatchedResponseV1OfValue> {
        override fun read(reader: ScaleCodecReader): BatchedResponseV1OfValue = try {
            BatchedResponseV1OfValue(
                Value.read(reader),
                ForwardCursor.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: BatchedResponseV1OfValue): Unit = try {
            Value.write(writer, instance.batch)
            ForwardCursor.write(writer, instance.cursor)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
