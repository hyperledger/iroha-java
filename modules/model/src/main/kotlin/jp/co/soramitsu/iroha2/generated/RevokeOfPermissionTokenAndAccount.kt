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
 * RevokeOfPermissionTokenAndAccount
 *
 * Generated from 'RevokeOfPermissionTokenAndAccount' regular structure
 */
public data class RevokeOfPermissionTokenAndAccount(
    public val `object`: PermissionToken,
    public val destinationId: AccountId,
) {
    public companion object :
        ScaleReader<RevokeOfPermissionTokenAndAccount>,
        ScaleWriter<RevokeOfPermissionTokenAndAccount> {
        override fun read(reader: ScaleCodecReader): RevokeOfPermissionTokenAndAccount = try {
            RevokeOfPermissionTokenAndAccount(
                PermissionToken.read(reader),
                AccountId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: RevokeOfPermissionTokenAndAccount): Unit = try {
            PermissionToken.write(writer, instance.`object`)
            AccountId.write(writer, instance.destinationId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
