//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import kotlin.Unit

/**
 * QueryResult
 *
 * Generated from 'iroha_data_model::query::QueryResult' tuple structure
 */
public data class QueryResult(
  public val `value`: Value
) {
  public companion object : ScaleReader<QueryResult>, ScaleWriter<QueryResult> {
    public override fun read(reader: ScaleCodecReader): QueryResult = QueryResult(
      Value.read(reader) as Value,
    )

    public override fun write(writer: ScaleCodecWriter, instance: QueryResult): Unit {
        Value.write(writer, instance.`value`)
    }
  }
}
