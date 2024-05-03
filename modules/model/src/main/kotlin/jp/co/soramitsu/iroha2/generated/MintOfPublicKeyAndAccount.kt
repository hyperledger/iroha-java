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
 * MintOfPublicKeyAndAccount
 *
 * Generated from 'MintOfPublicKeyAndAccount' regular structure
 */
public data class MintOfPublicKeyAndAccount(
    public val `object`: PublicKey,
    public val destinationId: AccountId,
) {
    public companion object :
        ScaleReader<MintOfPublicKeyAndAccount>,
        ScaleWriter<MintOfPublicKeyAndAccount> {
        override fun read(reader: ScaleCodecReader): MintOfPublicKeyAndAccount = try {
            MintOfPublicKeyAndAccount(
                PublicKey.read(reader),
                AccountId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: MintOfPublicKeyAndAccount): Unit = try {
            PublicKey.write(writer, instance.`object`)
            AccountId.write(writer, instance.destinationId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
