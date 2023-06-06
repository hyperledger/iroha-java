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
 * PermissionTokenFindError
 *
 * Generated from 'PermissionTokenFindError' regular structure
 */
public data class PermissionTokenFindError(
    public val accountId: AccountId,
    public val permissionTokenId: PermissionTokenId
) {
    public companion object :
        ScaleReader<PermissionTokenFindError>,
        ScaleWriter<PermissionTokenFindError> {
        public override fun read(reader: ScaleCodecReader): PermissionTokenFindError = try {
            PermissionTokenFindError(
                AccountId.read(reader),
                PermissionTokenId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PermissionTokenFindError) = try {
            AccountId.write(writer, instance.accountId)
            PermissionTokenId.write(writer, instance.permissionTokenId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
