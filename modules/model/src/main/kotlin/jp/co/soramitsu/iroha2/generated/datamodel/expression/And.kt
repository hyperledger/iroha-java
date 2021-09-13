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
 * And
 *
 * Generated from 'iroha_data_model::expression::And' regular structure
 */
public data class And(
    public val left: EvaluatesTo<Boolean>,
    public val right: EvaluatesTo<Boolean>
) {
    public companion object : ScaleReader<And>, ScaleWriter<And> {
        public override fun read(reader: ScaleCodecReader): And = try {
            And(
                EvaluatesTo.read(reader) as EvaluatesTo<Boolean>,
                EvaluatesTo.read(reader) as EvaluatesTo<Boolean>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: And) = try {
            EvaluatesTo.write(writer, instance.left)
            EvaluatesTo.write(writer, instance.right)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
