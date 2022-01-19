//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.signature.SignaturesOf
import jp.co.soramitsu.iroha2.wrapException

/**
 * Proof
 *
 * Generated from 'iroha_core::sumeragi::view_change::Proof' regular structure
 */
public data class Proof(
    public val payload: ProofPayload,
    public val signatures: SignaturesOf<Proof>
) {
    public companion object : ScaleReader<Proof>, ScaleWriter<Proof> {
        public override fun read(reader: ScaleCodecReader): Proof = try {
            Proof(
                ProofPayload.read(reader),
                SignaturesOf.read(reader) as SignaturesOf<Proof>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Proof) = try {
            ProofPayload.write(writer, instance.payload)
            SignaturesOf.write(writer, instance.signatures)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
