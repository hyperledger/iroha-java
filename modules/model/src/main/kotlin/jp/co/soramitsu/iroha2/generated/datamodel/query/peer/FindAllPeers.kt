//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.peer

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAllPeers
 *
 * Generated from 'iroha_data_model::query::peer::FindAllPeers' regular structure
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
    }
}
