//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.domain

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.String
import kotlin.Unit

/**
 * FindDomainByName
 *
 * Generated from 'iroha_data_model::query::domain::FindDomainByName' regular structure
 */
public data class FindDomainByName(
  public val name: EvaluatesTo<String>
) {
  public companion object : ScaleReader<FindDomainByName>, ScaleWriter<FindDomainByName> {
    public override fun read(reader: ScaleCodecReader): FindDomainByName = FindDomainByName(
      EvaluatesTo.read(reader) as EvaluatesTo<String>,
    )

    public override fun write(writer: ScaleCodecWriter, instance: FindDomainByName): Unit {
        EvaluatesTo.write(writer, instance.name)
    }
  }
}
