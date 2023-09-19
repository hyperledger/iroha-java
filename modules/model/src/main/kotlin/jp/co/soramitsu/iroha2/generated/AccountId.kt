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
    public val name: Name,
    public val domainId: DomainId,
) {
    public companion object : ScaleReader<AccountId>, ScaleWriter<AccountId> {
        override fun read(reader: ScaleCodecReader): AccountId = try {
            AccountId(
                Name.read(reader),
                DomainId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: AccountId): Unit = try {
            Name.write(writer, instance.name)
            DomainId.write(writer, instance.domainId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
