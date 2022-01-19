//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List

/**
 * ProofChain
 *
 * Generated from 'iroha_core::sumeragi::view_change::ProofChain' regular structure
 */
public data class ProofChain(
    public val proofs: List<Proof>
) {
    public companion object : ScaleReader<ProofChain>, ScaleWriter<ProofChain> {
        public override fun read(reader: ScaleCodecReader): ProofChain = try {
            ProofChain(
                reader.readVec(reader.readCompactInt()) { Proof.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: ProofChain) = try {
            writer.writeCompact(instance.proofs.size)
            instance.proofs.forEach { value -> Proof.write(writer, value) }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
