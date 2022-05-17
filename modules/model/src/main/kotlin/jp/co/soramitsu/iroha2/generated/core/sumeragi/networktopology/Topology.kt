//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.sumeragi.networktopology

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.core.block.VersionedCommittedBlock
import jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange.ProofChain
import jp.co.soramitsu.iroha2.generated.crypto.hash.HashOf
import jp.co.soramitsu.iroha2.generated.datamodel.peer.Id
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List

/**
 * Topology
 *
 * Generated from 'iroha_core::sumeragi::network_topology::Topology' regular structure
 */
public data class Topology(
    public val sortedPeers: List<Id>,
    public val atBlock: HashOf<VersionedCommittedBlock>,
    public val viewChangeProofs: ProofChain
) {
    public companion object : ScaleReader<Topology>, ScaleWriter<Topology> {
        public override fun read(reader: ScaleCodecReader): Topology = try {
            Topology(
                reader.readVec(reader.readCompactInt()) { Id.read(reader) },
                HashOf.read(reader) as HashOf<VersionedCommittedBlock>,
                ProofChain.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Topology) = try {
            writer.writeCompact(instance.sortedPeers.size)
            instance.sortedPeers.forEach { value ->
                Id.write(writer, value)
            }
            HashOf.write(writer, instance.atBlock)
            ProofChain.write(writer, instance.viewChangeProofs)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
