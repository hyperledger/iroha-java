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
 * BurnOfPublicKeyAndAccount
 *
 * Generated from 'BurnOfPublicKeyAndAccount' regular structure
 */
public data class BurnOfPublicKeyAndAccount(
    public val `object`: PublicKey,
    public val destinationId: AccountId,
) {
    public companion object :
        ScaleReader<BurnOfPublicKeyAndAccount>,
        ScaleWriter<BurnOfPublicKeyAndAccount> {
        override fun read(reader: ScaleCodecReader): BurnOfPublicKeyAndAccount = try {
            BurnOfPublicKeyAndAccount(
                PublicKey.read(reader),
                AccountId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: BurnOfPublicKeyAndAccount): Unit = try {
            PublicKey.write(writer, instance.`object`)
            AccountId.write(writer, instance.destinationId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
