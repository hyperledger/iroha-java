//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.account

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.Unit

/**
 * FindAccountById
 *
 * Generated from 'iroha_data_model::query::account::FindAccountById' regular structure
 */
public data class FindAccountById(
  public val id: EvaluatesTo<Id>
) {
  public companion object : ScaleReader<FindAccountById>, ScaleWriter<FindAccountById> {
    public override fun read(reader: ScaleCodecReader): FindAccountById = FindAccountById(
      EvaluatesTo.read(reader) as EvaluatesTo<Id>,
    )

    public override fun write(writer: ScaleCodecWriter, instance: FindAccountById): Unit {
        EvaluatesTo.write(writer, instance.id)
    }
  }
}
