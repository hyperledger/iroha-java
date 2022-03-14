//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.smartcontracts.isi.error

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.core.block.VersionedCommittedBlock
import jp.co.soramitsu.iroha2.generated.crypto.hash.HashOf
import jp.co.soramitsu.iroha2.wrapException

/**
 * ParentHashNotFound
 *
 * Generated from 'iroha_core::smartcontracts::isi::error::ParentHashNotFound' tuple structure
 */
public data class ParentHashNotFound(
    public val hashOf: HashOf<VersionedCommittedBlock>
) {
    public companion object : ScaleReader<ParentHashNotFound>, ScaleWriter<ParentHashNotFound> {
        public override fun read(reader: ScaleCodecReader): ParentHashNotFound = try {
            ParentHashNotFound(
                HashOf.read(reader) as HashOf<VersionedCommittedBlock>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: ParentHashNotFound) = try {
            HashOf.write(writer, instance.hashOf)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
