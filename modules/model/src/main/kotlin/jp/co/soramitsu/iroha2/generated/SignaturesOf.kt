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
import kotlin.collections.List

/**
 * SignaturesOf
 *
 * Generated from 'SignaturesOf<TransactionPayload>' regular structure
 */
public data class SignaturesOf<T0>(
    public val signatures: List<SignatureOf<TransactionPayload>>
) {
    public companion object : ScaleReader<SignaturesOf<out Any>>, ScaleWriter<SignaturesOf<out Any>> {
        public override fun read(reader: ScaleCodecReader): SignaturesOf<out Any> = try {
            SignaturesOf(
                reader.readVec(reader.readCompactInt()) {
                    SignatureOf.read(reader) as
                        SignatureOf<TransactionPayload>
                },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SignaturesOf<out Any>) = try {
            writer.writeCompact(instance.signatures.size)
            instance.signatures.forEach { value ->
                SignatureOf.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
