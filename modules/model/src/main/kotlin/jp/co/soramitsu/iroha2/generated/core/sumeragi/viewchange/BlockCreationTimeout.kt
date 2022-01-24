//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * BlockCreationTimeout
 *
 * Generated from 'iroha_core::sumeragi::view_change::BlockCreationTimeout' tuple structure
 */
public class BlockCreationTimeout {
    public companion object : ScaleReader<BlockCreationTimeout>, ScaleWriter<BlockCreationTimeout> {
        public override fun read(reader: ScaleCodecReader): BlockCreationTimeout = try {
            BlockCreationTimeout()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: BlockCreationTimeout) = try {
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
