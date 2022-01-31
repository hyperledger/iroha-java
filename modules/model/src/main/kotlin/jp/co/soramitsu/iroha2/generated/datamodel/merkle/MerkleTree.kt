//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.merkle

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any

/**
 * MerkleTree
 *
 * Generated from
 * 'iroha_data_model::merkle::MerkleTree<iroha_data_model::transaction::VersionedTransaction>' regular
 * structure
 */
public data class MerkleTree<T0>(
    public val rootNode: Node<VersionedTransaction>
) {
    public companion object : ScaleReader<MerkleTree<out Any>>, ScaleWriter<MerkleTree<out Any>> {
        public override fun read(reader: ScaleCodecReader): MerkleTree<out Any> = try {
            MerkleTree(
                Node.read(reader) as Node<VersionedTransaction>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: MerkleTree<out Any>) = try {
            Node.write(writer, instance.rootNode)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
