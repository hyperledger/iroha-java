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
import kotlin.String
import kotlin.Unit
import kotlin.collections.List

/**
 * ExecutorDataModel
 *
 * Generated from 'ExecutorDataModel' regular structure
 */
public data class ExecutorDataModel(
    public val permissions: List<PermissionId>,
    public val customInstruction: String? = null,
    public val schema: String,
) {
    public companion object : ScaleReader<ExecutorDataModel>, ScaleWriter<ExecutorDataModel> {
        override fun read(reader: ScaleCodecReader): ExecutorDataModel = try {
            ExecutorDataModel(
                reader.readVec(reader.readCompactInt()) { PermissionId.read(reader) },
                reader.readNullable(),
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: ExecutorDataModel): Unit = try {
            writer.writeCompact(instance.permissions.size)
            instance.permissions.sortedWith(
                PermissionId.comparator(),
            ).forEach { value ->
                PermissionId.write(writer, value)
            }
            writer.writeNullable(instance.customInstruction)
            writer.writeAsList(instance.schema.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
