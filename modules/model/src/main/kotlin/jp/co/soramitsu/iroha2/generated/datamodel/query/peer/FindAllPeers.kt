//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.peer

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.Boolean
import kotlin.Int

/**
 * FindAllPeers
 *
 * Generated from 'iroha_data_model::query::peer::FindAllPeers' tuple structure
 */
public class FindAllPeers {
    public companion object : ScaleReader<FindAllPeers>, ScaleWriter<FindAllPeers> {
        public override fun read(reader: ScaleCodecReader): FindAllPeers = try {
            FindAllPeers()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAllPeers) = try {
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public fun equals(o1: FindAllPeers, o2: Any?): Boolean = when (o2) {
            null -> false
            else -> o2::class == o1::class
        }

        public override fun hashCode(): Int = "datamodel.query.peer.FindAllPeers".hashCode()
    }
}
