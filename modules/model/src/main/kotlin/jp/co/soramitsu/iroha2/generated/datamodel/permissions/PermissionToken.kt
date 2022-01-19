//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.permissions

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.wrapException

/**
 * PermissionToken
 *
 * Generated from 'iroha_data_model::permissions::PermissionToken' regular structure
 */
public data class PermissionToken(
    public val name: Name,
    public val params: Map<Name, Value>
) {
    public companion object : ScaleReader<PermissionToken>, ScaleWriter<PermissionToken> {
        public override fun read(reader: ScaleCodecReader): PermissionToken = try {
            PermissionToken(
                Name.read(reader),
                reader.readMap(reader.readCompactInt(), { Name.read(reader) }, { Value.read(reader) }),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PermissionToken) = try {
            Name.write(writer, instance.name)
            writer.writeCompact(instance.params.size)
            instance.params.forEach { (key, value) ->
                Name.write(writer, key)
                Value.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
