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
import kotlin.Unit

/**
 * FindPermissionTokensByAccountId
 *
 * Generated from 'iroha_data_model::query::permissions::FindPermissionTokensByAccountId' regular
 * structure
 */
public class FindPermissionTokensByAccountId(
  public val id: EvaluatesTo<Id>
) {
  public companion object : ScaleReader<FindPermissionTokensByAccountId>,
      ScaleWriter<FindPermissionTokensByAccountId> {
    public override fun read(reader: ScaleCodecReader): FindPermissionTokensByAccountId =
        FindPermissionTokensByAccountId(Id.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: FindPermissionTokensByAccountId):
        Unit {
      Id.write(writer, instance.id)
    }
  }
}
