//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.account

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.wrapException

/**
 * AccountId
 *
 * Generated from 'iroha_data_model::account::AccountId' regular structure
 */
public data class AccountId(
    public val name: Name,
    public val domainId: DomainId
) {
    public companion object : ScaleReader<AccountId>, ScaleWriter<AccountId> {
        public override fun read(reader: ScaleCodecReader): AccountId = try {
            AccountId(
                Name.read(reader),
                DomainId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AccountId) = try {
            Name.write(writer, instance.name)
            DomainId.write(writer, instance.domainId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
