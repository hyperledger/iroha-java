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
 * BatchedResponseOfValue
 *
 * Generated from 'BatchedResponseOfValue' regular structure
 */
public data class BatchedResponseOfValue(
    public val batch: Value,
    public val cursor: ForwardCursor,
) {
    public companion object : ScaleReader<BatchedResponseOfValue>, ScaleWriter<BatchedResponseOfValue> {
        override fun read(reader: ScaleCodecReader): BatchedResponseOfValue = try {
            BatchedResponseOfValue(
                Value.read(reader),
                ForwardCursor.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: BatchedResponseOfValue): Unit = try {
            Value.write(writer, instance.batch)
            ForwardCursor.write(writer, instance.cursor)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
