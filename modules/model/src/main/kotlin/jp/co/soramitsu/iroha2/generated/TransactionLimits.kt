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
import kotlin.Unit

/**
 * TransactionLimits
 *
 * Generated from 'TransactionLimits' regular structure
 */
public data class TransactionLimits(
    public val maxInstructionNumber: BigInteger,
    public val maxWasmSizeBytes: BigInteger,
) {
    public companion object : ScaleReader<TransactionLimits>, ScaleWriter<TransactionLimits> {
        override fun read(reader: ScaleCodecReader): TransactionLimits = try {
            TransactionLimits(
                reader.readUint64(),
                reader.readUint64(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: TransactionLimits): Unit = try {
            writer.writeUint64(instance.maxInstructionNumber)
            writer.writeUint64(instance.maxWasmSizeBytes)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
