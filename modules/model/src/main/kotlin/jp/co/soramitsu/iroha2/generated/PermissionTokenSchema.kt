//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String
import kotlin.Unit
import kotlin.collections.List

/**
 * PermissionTokenSchema
 *
 * Generated from 'PermissionTokenSchema' regular structure
 */
public data class PermissionTokenSchema(
    public val tokenIds: List<Name>,
    public val schema: String,
) {
    public companion object : ScaleReader<PermissionTokenSchema>, ScaleWriter<PermissionTokenSchema> {
        override fun read(reader: ScaleCodecReader): PermissionTokenSchema = try {
            PermissionTokenSchema(
                reader.readVec(reader.readCompactInt()) { Name.read(reader) },
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: PermissionTokenSchema): Unit = try {
            writer.writeCompact(instance.tokenIds.size)
            instance.tokenIds.forEach { value ->
                Name.write(writer, value)
            }
            writer.writeAsList(instance.schema.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
