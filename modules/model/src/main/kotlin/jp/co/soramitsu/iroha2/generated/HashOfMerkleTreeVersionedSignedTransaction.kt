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
 * HashOfMerkleTreeVersionedSignedTransaction
 *
 * Generated from 'HashOfMerkleTreeVersionedSignedTransaction' regular structure
 */
public data class HashOfMerkleTreeVersionedSignedTransaction(
    public val hash: Hash,
) {
    public companion object :
        ScaleReader<HashOfMerkleTreeVersionedSignedTransaction>,
        ScaleWriter<HashOfMerkleTreeVersionedSignedTransaction> {
        override fun read(reader: ScaleCodecReader): HashOfMerkleTreeVersionedSignedTransaction = try {
            HashOfMerkleTreeVersionedSignedTransaction(
                Hash.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(
            writer: ScaleCodecWriter,
            instance: HashOfMerkleTreeVersionedSignedTransaction,
        ): Unit = try {
            Hash.write(writer, instance.hash)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
