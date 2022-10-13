//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.block

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindBlockHeaderByHash
 *
 * Generated from 'iroha_data_model::query::block::FindBlockHeaderByHash' regular structure
 */
public data class FindBlockHeaderByHash(
    public val hash: EvaluatesTo<Hash>
) {
    public companion object : ScaleReader<FindBlockHeaderByHash>, ScaleWriter<FindBlockHeaderByHash> {
        public override fun read(reader: ScaleCodecReader): FindBlockHeaderByHash = try {
            FindBlockHeaderByHash(
                EvaluatesTo.read(reader) as EvaluatesTo<Hash>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindBlockHeaderByHash) = try {
            EvaluatesTo.write(writer, instance.hash)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
