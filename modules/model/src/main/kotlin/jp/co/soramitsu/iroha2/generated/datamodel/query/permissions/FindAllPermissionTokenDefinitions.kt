//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.permissions

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAllPermissionTokenDefinitions
 *
 * Generated from 'iroha_data_model::query::permissions::FindAllPermissionTokenDefinitions' tuple
 * structure
 */
public class FindAllPermissionTokenDefinitions {
    public companion object :
        ScaleReader<FindAllPermissionTokenDefinitions>,
        ScaleWriter<FindAllPermissionTokenDefinitions> {
        public override fun read(reader: ScaleCodecReader): FindAllPermissionTokenDefinitions = try {
            FindAllPermissionTokenDefinitions()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAllPermissionTokenDefinitions) =
            try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
