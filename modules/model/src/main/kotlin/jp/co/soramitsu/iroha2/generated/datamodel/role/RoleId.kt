//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.role

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.wrapException

/**
 * RoleId
 *
 * Generated from 'iroha_data_model::role::RoleId' regular structure
 */
public data class RoleId(
    public val name: Name
) {
    public companion object : ScaleReader<RoleId>, ScaleWriter<RoleId> {
        public override fun read(reader: ScaleCodecReader): RoleId = try {
            RoleId(
                Name.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: RoleId) = try {
            Name.write(writer, instance.name)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
