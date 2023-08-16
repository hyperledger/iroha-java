//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit

/**
 * PermissionTokenSchemaUpdateEvent
 *
 * Generated from 'PermissionTokenSchemaUpdateEvent' regular structure
 */
public data class PermissionTokenSchemaUpdateEvent(
    public val oldSchema: PermissionTokenSchema,
    public val newSchema: PermissionTokenSchema,
) {
    public companion object :
        ScaleReader<PermissionTokenSchemaUpdateEvent>,
        ScaleWriter<PermissionTokenSchemaUpdateEvent> {
        override fun read(reader: ScaleCodecReader): PermissionTokenSchemaUpdateEvent = try {
            PermissionTokenSchemaUpdateEvent(
                PermissionTokenSchema.read(reader),
                PermissionTokenSchema.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: PermissionTokenSchemaUpdateEvent): Unit =
            try {
                PermissionTokenSchema.write(writer, instance.oldSchema)
                PermissionTokenSchema.write(writer, instance.newSchema)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
