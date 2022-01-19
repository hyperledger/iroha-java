//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.schema.irohacrypto.hash.HashOf
import jp.co.soramitsu.iroha2.wrapException

/**
 * CommitTimeout
 *
 * Generated from 'iroha_core::sumeragi::view_change::CommitTimeout' regular structure
 */
public data class CommitTimeout(
    public val hash: HashOf<out Any>
) {
    public companion object : ScaleReader<CommitTimeout>, ScaleWriter<CommitTimeout> {
        public override fun read(reader: ScaleCodecReader): CommitTimeout = try {
            CommitTimeout(
                HashOf.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: CommitTimeout) = try {
            HashOf.write(writer, instance.hash)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
