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
 * GrantOfPermissionTokenAndAccount
 *
 * Generated from 'GrantOfPermissionTokenAndAccount' regular structure
 */
public data class GrantOfPermissionTokenAndAccount(
    public val `object`: PermissionToken,
    public val destinationId: AccountId,
) {
    public companion object :
        ScaleReader<GrantOfPermissionTokenAndAccount>,
        ScaleWriter<GrantOfPermissionTokenAndAccount> {
        override fun read(reader: ScaleCodecReader): GrantOfPermissionTokenAndAccount = try {
            GrantOfPermissionTokenAndAccount(
                PermissionToken.read(reader),
                AccountId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: GrantOfPermissionTokenAndAccount): Unit =
            try {
                PermissionToken.write(writer, instance.`object`)
                AccountId.write(writer, instance.destinationId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
