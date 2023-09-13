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
 * HashOfVersionedSignedTransaction
 *
 * Generated from 'HashOfVersionedSignedTransaction' regular structure
 */
public data class HashOfVersionedSignedTransaction(
    public val hash: Hash,
) {
    public companion object :
        ScaleReader<HashOfVersionedSignedTransaction>,
        ScaleWriter<HashOfVersionedSignedTransaction> {
        override fun read(reader: ScaleCodecReader): HashOfVersionedSignedTransaction = try {
            HashOfVersionedSignedTransaction(
                Hash.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: HashOfVersionedSignedTransaction): Unit =
            try {
                Hash.write(writer, instance.hash)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
