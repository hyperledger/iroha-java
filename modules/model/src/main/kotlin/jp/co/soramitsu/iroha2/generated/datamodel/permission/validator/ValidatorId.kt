//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.permission.validator

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.wrapException

/**
 * ValidatorId
 *
 * Generated from 'iroha_data_model::permission::validator::ValidatorId' regular structure
 */
public data class ValidatorId(
    public val name: Name,
    public val accountId: AccountId
) {
    public companion object : ScaleReader<ValidatorId>, ScaleWriter<ValidatorId> {
        public override fun read(reader: ScaleCodecReader): ValidatorId = try {
            ValidatorId(
                Name.read(reader),
                AccountId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: ValidatorId) = try {
            Name.write(writer, instance.name)
            AccountId.write(writer, instance.accountId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
