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
 * BlockSubscriptionRequest
 *
 * Generated from 'BlockSubscriptionRequest' regular structure
 */
public data class BlockSubscriptionRequest(
    public val u64: BigInteger
) {
    public companion object :
        ScaleReader<BlockSubscriptionRequest>,
        ScaleWriter<BlockSubscriptionRequest> {
        public override fun read(reader: ScaleCodecReader): BlockSubscriptionRequest = try {
            BlockSubscriptionRequest(
                reader.readUint64(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: BlockSubscriptionRequest) = try {
            writer.writeUint64(instance.u64)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
