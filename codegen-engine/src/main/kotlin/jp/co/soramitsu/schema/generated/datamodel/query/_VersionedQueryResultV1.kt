// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.query

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleReader

/**
 * _VersionedQueryResultV1
 *
 * Generated from 'iroha_data_model::query::_VersionedQueryResultV1' tuple structure
 */
public class _VersionedQueryResultV1(
  private val queryResult: QueryResult
) {
  public companion object READER : ScaleReader<_VersionedQueryResultV1> {
    public override fun read(reader: ScaleCodecReader): _VersionedQueryResultV1 =
        _VersionedQueryResultV1(jp.co.soramitsu.schema.generated.datamodel.query.QueryResult.READER.read(reader))
  }
}
