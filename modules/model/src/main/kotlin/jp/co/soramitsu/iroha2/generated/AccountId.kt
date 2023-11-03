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
 * AccountId
 *
 * Generated from 'AccountId' regular structure
 */
public data class AccountId(
    public val domainId: DomainId,
    public val name: Name,
) {
    public companion object : ScaleReader<AccountId>, ScaleWriter<AccountId> {
        override fun read(reader: ScaleCodecReader): AccountId = try {
            AccountId(
                DomainId.read(reader),
                Name.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: AccountId): Unit = try {
            DomainId.write(writer, instance.domainId)
            Name.write(writer, instance.name)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
