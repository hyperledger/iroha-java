//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.block

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAllBlockHeaders
 *
 * Generated from 'iroha_data_model::query::block::FindAllBlockHeaders' tuple structure
 */
public class FindAllBlockHeaders {
    public companion object : ScaleReader<FindAllBlockHeaders>, ScaleWriter<FindAllBlockHeaders> {
        public override fun read(reader: ScaleCodecReader): FindAllBlockHeaders = try {
            FindAllBlockHeaders()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAllBlockHeaders) = try {
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
