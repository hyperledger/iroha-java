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
 * TransactionExpired
 *
 * Generated from 'TransactionExpired' regular structure
 */
public data class TransactionExpired(
    public val timeToLiveMs: BigInteger
) {
    public companion object : ScaleReader<TransactionExpired>, ScaleWriter<TransactionExpired> {
        public override fun read(reader: ScaleCodecReader): TransactionExpired = try {
            TransactionExpired(
                reader.readUint64(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: TransactionExpired) = try {
            writer.writeUint64(instance.timeToLiveMs)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
