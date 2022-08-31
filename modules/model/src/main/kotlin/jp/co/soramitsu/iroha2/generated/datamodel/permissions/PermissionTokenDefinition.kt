//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.permissions

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * PermissionTokenDefinition
 *
 * Generated from 'iroha_data_model::permissions::PermissionTokenDefinition' regular structure
 */
public data class PermissionTokenDefinition(
    public val id: PermissionsId
) {
    public companion object :
        ScaleReader<PermissionTokenDefinition>,
        ScaleWriter<PermissionTokenDefinition> {
        public override fun read(reader: ScaleCodecReader): PermissionTokenDefinition = try {
            PermissionTokenDefinition(
                PermissionsId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PermissionTokenDefinition) = try {
            PermissionsId.write(writer, instance.id)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
