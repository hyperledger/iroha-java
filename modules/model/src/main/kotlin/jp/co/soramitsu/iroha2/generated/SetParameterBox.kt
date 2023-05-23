//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * SetParameterBox
 *
 * Generated from 'SetParameterBox' regular structure
 */
public data class SetParameterBox(
    public val parameter: EvaluatesTo<Parameter>
) {
    public companion object : ScaleReader<SetParameterBox>, ScaleWriter<SetParameterBox> {
        public override fun read(reader: ScaleCodecReader): SetParameterBox = try {
            SetParameterBox(
                EvaluatesTo.read(reader) as EvaluatesTo<Parameter>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SetParameterBox) = try {
            EvaluatesTo.write(writer, instance.parameter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
