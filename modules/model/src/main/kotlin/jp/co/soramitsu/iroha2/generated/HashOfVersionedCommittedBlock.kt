//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit

/**
 * HashOfVersionedCommittedBlock
 *
 * Generated from 'HashOfVersionedCommittedBlock' regular structure
 */
public data class HashOfVersionedCommittedBlock(
    public val hash: Hash,
) {
    public companion object :
        ScaleReader<HashOfVersionedCommittedBlock>,
        ScaleWriter<HashOfVersionedCommittedBlock> {
        override fun read(reader: ScaleCodecReader): HashOfVersionedCommittedBlock = try {
            HashOfVersionedCommittedBlock(
                Hash.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: HashOfVersionedCommittedBlock): Unit =
            try {
                Hash.write(writer, instance.hash)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
