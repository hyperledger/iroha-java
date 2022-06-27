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
 * FindAllBlocks
 *
 * Generated from 'iroha_data_model::query::block::FindAllBlocks' tuple structure
 */
public class FindAllBlocks {
    public companion object : ScaleReader<FindAllBlocks>, ScaleWriter<FindAllBlocks> {
        public override fun read(reader: ScaleCodecReader): FindAllBlocks = try {
            FindAllBlocks()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAllBlocks) = try {
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
