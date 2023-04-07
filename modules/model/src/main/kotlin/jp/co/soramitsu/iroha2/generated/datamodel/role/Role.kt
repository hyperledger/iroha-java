//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.role

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.comparator
import jp.co.soramitsu.iroha2.generated.datamodel.permission.token.Token
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List

/**
 * Role
 *
 * Generated from 'iroha_data_model::role::Role' regular structure
 */
public data class Role(
    public val id: RoleId,
    public val permissions: List<Token>
) {
    public companion object : ScaleReader<Role>, ScaleWriter<Role> {
        public override fun read(reader: ScaleCodecReader): Role = try {
            Role(
                RoleId.read(reader),
                reader.readVec(reader.readCompactInt()) { Token.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Role) = try {
            RoleId.write(writer, instance.id)
            writer.writeCompact(instance.permissions.size)
            instance.permissions.sortedWith(
                Token.comparator()
            ).forEach { value ->
                Token.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
