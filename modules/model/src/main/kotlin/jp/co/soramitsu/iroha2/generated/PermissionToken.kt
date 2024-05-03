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
 * PermissionToken
 *
 * Generated from 'PermissionToken' regular structure
 */
public data class PermissionToken(
    public val definitionId: Name,
    public val payload: JsonString,
) {
    public companion object : ScaleReader<PermissionToken>, ScaleWriter<PermissionToken> {
        override fun read(reader: ScaleCodecReader): PermissionToken = try {
            PermissionToken(
                Name.read(reader),
                JsonString.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: PermissionToken): Unit = try {
            Name.write(writer, instance.definitionId)
            JsonString.write(writer, instance.payload)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
