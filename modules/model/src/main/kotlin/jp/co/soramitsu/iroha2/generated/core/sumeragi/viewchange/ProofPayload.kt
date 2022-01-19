//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.core.block.VersionedCommittedBlock
import jp.co.soramitsu.iroha2.generated.schema.irohacrypto.hash.HashOf
import jp.co.soramitsu.iroha2.wrapException

/**
 * ProofPayload
 *
 * Generated from 'iroha_core::sumeragi::view_change::ProofPayload' regular structure
 */
public data class ProofPayload(
    public val previousProof: HashOf<Proof>,
    public val latestBlock: HashOf<VersionedCommittedBlock>,
    public val reason: Reason
) {
    public companion object : ScaleReader<ProofPayload>, ScaleWriter<ProofPayload> {
        public override fun read(reader: ScaleCodecReader): ProofPayload = try {
            ProofPayload(
                HashOf.read(reader) as HashOf<Proof>,
                HashOf.read(reader) as HashOf<VersionedCommittedBlock>,
                Reason.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: ProofPayload) = try {
            HashOf.write(writer, instance.previousProof)
            HashOf.write(writer, instance.latestBlock)
            Reason.write(writer, instance.reason)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
