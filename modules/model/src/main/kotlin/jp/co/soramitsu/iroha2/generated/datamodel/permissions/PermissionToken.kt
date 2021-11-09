//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.permissions

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String
import kotlin.collections.Map

/**
 * PermissionToken
 *
 * Generated from 'iroha_data_model::permissions::PermissionToken' regular structure
 */
public data class PermissionToken(
    public val name: String,
    public val params: Map<String, Value>
) {
    public companion object : ScaleReader<PermissionToken>, ScaleWriter<PermissionToken> {
        public override fun read(reader: ScaleCodecReader): PermissionToken = try {
            PermissionToken(
                reader.readString(),
                reader.readMap(reader.readCompactInt(), { reader.readString() }, { Value.read(reader) }),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PermissionToken) = try {
            writer.writeAsList(instance.name.toByteArray(Charsets.UTF_8))
            writer.writeCompact(instance.params.size)
            instance.params.forEach { (key, value) ->  
                writer.writeAsList(key.toByteArray(Charsets.UTF_8))
                Value.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
