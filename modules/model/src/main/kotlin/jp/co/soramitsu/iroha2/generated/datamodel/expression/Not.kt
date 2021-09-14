//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Boolean

/**
 * Not
 *
 * Generated from 'iroha_data_model::expression::Not' regular structure
 */
public data class Not(
    public val expression: EvaluatesTo<Boolean>
) {
    public companion object : ScaleReader<Not>, ScaleWriter<Not> {
        public override fun read(reader: ScaleCodecReader): Not = try {
            Not(
                EvaluatesTo.read(reader) as EvaluatesTo<Boolean>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Not) = try {
            EvaluatesTo.write(writer, instance.expression)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
