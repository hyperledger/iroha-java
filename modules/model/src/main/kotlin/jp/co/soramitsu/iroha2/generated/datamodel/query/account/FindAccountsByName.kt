//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.account

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.String

/**
 * FindAccountsByName
 *
 * Generated from 'iroha_data_model::query::account::FindAccountsByName' regular structure
 */
public data class FindAccountsByName(
    public val name: EvaluatesTo<String>
) {
    public companion object : ScaleReader<FindAccountsByName>, ScaleWriter<FindAccountsByName> {
        public override fun read(reader: ScaleCodecReader): FindAccountsByName = FindAccountsByName(
            EvaluatesTo.read(reader) as EvaluatesTo<String>,
        )

        public override fun write(writer: ScaleCodecWriter, instance: FindAccountsByName) {
            EvaluatesTo.write(writer, instance.name)
        }
    }
}
