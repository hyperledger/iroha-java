//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.merkle

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.hash.HashOf
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any

/**
 * Subtree
 *
 * Generated from
 * 'iroha_data_model::merkle::Subtree<iroha_data_model::transaction::VersionedTransaction>' regular
 * structure
 */
public data class Subtree<T0>(
    public val left: Node<VersionedTransaction>,
    public val right: Node<VersionedTransaction>,
    public val hash: HashOf<Node<VersionedTransaction>>
) {
    public companion object : ScaleReader<Subtree<out Any>>, ScaleWriter<Subtree<out Any>> {
        public override fun read(reader: ScaleCodecReader): Subtree<out Any> = try {
            Subtree(
                Node.read(reader) as Node<VersionedTransaction>,
                Node.read(reader) as Node<VersionedTransaction>,
                HashOf.read(reader) as HashOf<Node<VersionedTransaction>>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Subtree<out Any>) = try {
            Node.write(writer, instance.left)
            Node.write(writer, instance.right)
            HashOf.write(writer, instance.hash)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
