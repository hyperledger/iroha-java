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
 * MintOfSignatureCheckConditionAndAccount
 *
 * Generated from 'MintOfSignatureCheckConditionAndAccount' regular structure
 */
public data class MintOfSignatureCheckConditionAndAccount(
    public val `object`: SignatureCheckCondition,
    public val destinationId: AccountId,
) {
    public companion object :
        ScaleReader<MintOfSignatureCheckConditionAndAccount>,
        ScaleWriter<MintOfSignatureCheckConditionAndAccount> {
        override fun read(reader: ScaleCodecReader): MintOfSignatureCheckConditionAndAccount = try {
            MintOfSignatureCheckConditionAndAccount(
                SignatureCheckCondition.read(reader),
                AccountId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: MintOfSignatureCheckConditionAndAccount): Unit = try {
            SignatureCheckCondition.write(writer, instance.`object`)
            AccountId.write(writer, instance.destinationId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
