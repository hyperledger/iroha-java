//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.Parameter
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * SetParameterBox
 *
 * Generated from 'iroha_data_model::isi::SetParameterBox' regular structure
 */
public data class SetParameterBox(
    public val parameter: EvaluatesTo<Parameter>,
    public val sourceId: EvaluatesTo<IdBox>
) {
    public companion object : ScaleReader<SetParameterBox>, ScaleWriter<SetParameterBox> {
        public override fun read(reader: ScaleCodecReader): SetParameterBox = try {
            SetParameterBox(
                EvaluatesTo.read(reader) as EvaluatesTo<Parameter>,
                EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SetParameterBox) = try {
            EvaluatesTo.write(writer, instance.parameter)
            EvaluatesTo.write(writer, instance.sourceId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
