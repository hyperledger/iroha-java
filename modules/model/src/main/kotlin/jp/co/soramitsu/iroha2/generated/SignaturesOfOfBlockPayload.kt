//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.comparator
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit
import kotlin.collections.List

/**
 * SignaturesOfOfBlockPayload
 *
 * Generated from 'SignaturesOfOfBlockPayload' regular structure
 */
public data class SignaturesOfOfBlockPayload(
    public val signatures: List<SignatureOf<BlockPayload>>,
) {
    public companion object :
        ScaleReader<SignaturesOfOfBlockPayload>,
        ScaleWriter<SignaturesOfOfBlockPayload> {
        override fun read(reader: ScaleCodecReader): SignaturesOfOfBlockPayload = try {
            SignaturesOfOfBlockPayload(
                reader.readVec(reader.readCompactInt()) {
                    SignatureOf.read(reader) as
                        SignatureOf<BlockPayload>
                },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: SignaturesOfOfBlockPayload): Unit = try {
            writer.writeCompact(instance.signatures.size)
            instance.signatures.sortedWith(
                SignatureOf.comparator(),
            ).forEach { value ->
                SignatureOf.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
