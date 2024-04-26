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
 * OriginFilterOfAccountEvent
 *
 * Generated from 'OriginFilterOfAccountEvent' regular structure
 */
public data class OriginFilterOfAccountEvent(
    public val accountId: AccountId,
) {
    public companion object :
        ScaleReader<OriginFilterOfAccountEvent>,
        ScaleWriter<OriginFilterOfAccountEvent> {
        override fun read(reader: ScaleCodecReader): OriginFilterOfAccountEvent = try {
            OriginFilterOfAccountEvent(
                AccountId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: OriginFilterOfAccountEvent): Unit = try {
            AccountId.write(writer, instance.accountId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
