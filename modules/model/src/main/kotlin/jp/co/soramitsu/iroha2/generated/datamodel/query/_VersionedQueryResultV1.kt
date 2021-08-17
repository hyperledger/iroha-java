//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter

/**
 * _VersionedQueryResultV1
 *
 * Generated from 'iroha_data_model::query::_VersionedQueryResultV1' tuple structure
 */
public data class _VersionedQueryResultV1(
    public val queryResult: QueryResult
) {
    public companion object :
        ScaleReader<_VersionedQueryResultV1>,
        ScaleWriter<_VersionedQueryResultV1> {
        public override fun read(reader: ScaleCodecReader): _VersionedQueryResultV1 =
            _VersionedQueryResultV1(
                QueryResult.read(reader) as QueryResult,
            )

        public override fun write(writer: ScaleCodecWriter, instance: _VersionedQueryResultV1) {
            QueryResult.write(writer, instance.queryResult)
        }
    }
}
