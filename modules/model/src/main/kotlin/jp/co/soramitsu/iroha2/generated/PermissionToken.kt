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
 * PermissionToken
 *
 * Generated from 'PermissionToken' regular structure
 */
public data class PermissionToken(
    public val definitionId: PermissionTokenId,
    public val params: Map<Name, Value>
) {
    public companion object : ScaleReader<PermissionToken>, ScaleWriter<PermissionToken> {
        public override fun read(reader: ScaleCodecReader): PermissionToken = try {
            PermissionToken(
                PermissionTokenId.read(reader),
                reader.readMap(reader.readCompactInt(), { Name.read(reader) }, { Value.read(reader) }),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PermissionToken) = try {
            PermissionTokenId.write(writer, instance.definitionId)
            writer.writeCompact(instance.params.size)
            instance.params.toSortedMap(
                Name.comparator()
            ).forEach { (key, value) ->
                Name.write(writer, key)
                Value.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
