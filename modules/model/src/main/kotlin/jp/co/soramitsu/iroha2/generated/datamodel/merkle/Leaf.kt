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
 * Leaf
 *
 * Generated from
 * 'iroha_data_model::merkle::Leaf<iroha_data_model::transaction::VersionedTransaction>' regular
 * structure
 */
public data class Leaf<T0>(
    public val hash: HashOf<VersionedTransaction>
) {
    public companion object : ScaleReader<Leaf<out Any>>, ScaleWriter<Leaf<out Any>> {
        public override fun read(reader: ScaleCodecReader): Leaf<out Any> = try {
            Leaf(
                HashOf.read(reader) as HashOf<VersionedTransaction>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Leaf<out Any>) = try {
            HashOf.write(writer, instance.hash)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
