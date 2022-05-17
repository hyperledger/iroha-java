//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.role

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAllRoleIds
 *
 * Generated from 'iroha_data_model::query::role::FindAllRoleIds' tuple structure
 */
public class FindAllRoleIds {
    public companion object : ScaleReader<FindAllRoleIds>, ScaleWriter<FindAllRoleIds> {
        public override fun read(reader: ScaleCodecReader): FindAllRoleIds = try {
            FindAllRoleIds()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAllRoleIds) = try {
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
