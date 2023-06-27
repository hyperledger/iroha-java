//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * BlockMessage
 *
 * Generated from 'BlockMessage' regular structure
 */
public data class BlockMessage(
    public val versionedCommittedBlock: VersionedCommittedBlock
) {
    public companion object : ScaleReader<BlockMessage>, ScaleWriter<BlockMessage> {
        public override fun read(reader: ScaleCodecReader): BlockMessage = try {
            BlockMessage(
                VersionedCommittedBlock.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: BlockMessage) = try {
            VersionedCommittedBlock.write(writer, instance.versionedCommittedBlock)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
