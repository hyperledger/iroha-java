//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.permissions

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.hashMapWithSize
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String
import kotlin.collections.MutableMap

/**
 * PermissionToken
 *
 * Generated from 'iroha_data_model::permissions::PermissionToken' regular structure
 */
public data class PermissionToken(
    public val name: String,
    public val params: MutableMap<String, Value>
) {
    public companion object : ScaleReader<PermissionToken>, ScaleWriter<PermissionToken> {
        public override fun read(reader: ScaleCodecReader): PermissionToken = try {
            PermissionToken(
                reader.readString(),
                hashMapWithSize(reader.readCompactInt(), { reader.readString() }, { Value.read(reader) }),
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
