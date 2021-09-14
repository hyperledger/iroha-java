//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.permissions

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindPermissionTokensByAccountId
 *
 * Generated from 'iroha_data_model::query::permissions::FindPermissionTokensByAccountId' regular
 * structure
 */
public data class FindPermissionTokensByAccountId(
    public val id: EvaluatesTo<Id>
) {
    public companion object :
        ScaleReader<FindPermissionTokensByAccountId>,
        ScaleWriter<FindPermissionTokensByAccountId> {
        public override fun read(reader: ScaleCodecReader): FindPermissionTokensByAccountId = try {
            FindPermissionTokensByAccountId(
                EvaluatesTo.read(reader) as EvaluatesTo<Id>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindPermissionTokensByAccountId) =
            try {
                EvaluatesTo.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
