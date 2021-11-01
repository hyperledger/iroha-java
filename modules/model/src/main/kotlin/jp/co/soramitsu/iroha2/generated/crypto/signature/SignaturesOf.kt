//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.crypto.signature

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.hashMapWithSize
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.collections.Map

/**
 * SignaturesOf
 *
 * Generated from 'iroha_crypto::signature::SignaturesOf<iroha_data_model::transaction::Payload>'
 * regular structure
 */
public data class SignaturesOf<T0>(
    public val signatures: Map<PublicKey, Signature>
) {
    public companion object : ScaleReader<SignaturesOf<out Any>>, ScaleWriter<SignaturesOf<out Any>> {
        public override fun read(reader: ScaleCodecReader): SignaturesOf<out Any> = try {
            SignaturesOf(
                hashMapWithSize(
                    reader.readCompactInt(), { PublicKey.read(reader) },
                    { Signature.read(reader) }
                ),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SignaturesOf<out Any>) = try {
            writer.writeCompact(instance.signatures.size)
            instance.signatures.forEach { (key, value) ->  
                PublicKey.write(writer, key)
                Signature.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
