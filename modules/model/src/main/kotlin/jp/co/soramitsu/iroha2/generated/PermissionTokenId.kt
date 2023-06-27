//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * PermissionTokenId
 *
 * Generated from 'PermissionTokenId' regular structure
 */
public data class PermissionTokenId(
    public val name: Name
) {
    public companion object : ScaleReader<PermissionTokenId>, ScaleWriter<PermissionTokenId> {
        public override fun read(reader: ScaleCodecReader): PermissionTokenId = try {
            PermissionTokenId(
                Name.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PermissionTokenId) = try {
            Name.write(writer, instance.name)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
