//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.comparator
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.Map

/**
 * PermissionTokenDefinition
 *
 * Generated from 'PermissionTokenDefinition' regular structure
 */
public data class PermissionTokenDefinition(
    public val id: PermissionTokenId,
    public val params: Map<Name, ValueKind>
) {
    public companion object :
        ScaleReader<PermissionTokenDefinition>,
        ScaleWriter<PermissionTokenDefinition> {
        public override fun read(reader: ScaleCodecReader): PermissionTokenDefinition = try {
            PermissionTokenDefinition(
                PermissionTokenId.read(reader),
                reader.readMap(reader.readCompactInt(), { Name.read(reader) }, { ValueKind.read(reader) }),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PermissionTokenDefinition) = try {
            PermissionTokenId.write(writer, instance.id)
            writer.writeCompact(instance.params.size)
            instance.params.toSortedMap(
                Name.comparator()
            ).forEach { (key, value) ->
                Name.write(writer, key)
                ValueKind.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
