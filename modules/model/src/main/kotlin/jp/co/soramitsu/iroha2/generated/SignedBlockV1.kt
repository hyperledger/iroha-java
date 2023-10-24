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
 * SignedBlockV1
 *
 * Generated from 'SignedBlockV1' regular structure
 */
public data class SignedBlockV1(
    public val signatures: SignaturesOfOfBlockPayload,
    public val payload: BlockPayload,
) {
    public companion object : ScaleReader<SignedBlockV1>, ScaleWriter<SignedBlockV1> {
        override fun read(reader: ScaleCodecReader): SignedBlockV1 = try {
            SignedBlockV1(
                SignaturesOfOfBlockPayload.read(reader),
                BlockPayload.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: SignedBlockV1): Unit = try {
            SignaturesOfOfBlockPayload.write(writer, instance.signatures)
            BlockPayload.write(writer, instance.payload)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
