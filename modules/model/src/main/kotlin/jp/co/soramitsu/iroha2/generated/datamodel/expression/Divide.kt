//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.UInt

/**
 * Divide
 *
 * Generated from 'iroha_data_model::expression::Divide' regular structure
 */
public data class Divide(
    public val left: EvaluatesTo<UInt>,
    public val right: EvaluatesTo<UInt>
) {
    public companion object : ScaleReader<Divide>, ScaleWriter<Divide> {
        public override fun read(reader: ScaleCodecReader): Divide = try {
            Divide(
                EvaluatesTo.read(reader) as EvaluatesTo<UInt>,
                EvaluatesTo.read(reader) as EvaluatesTo<UInt>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Divide) = try {
            EvaluatesTo.write(writer, instance.left)
            EvaluatesTo.write(writer, instance.right)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
