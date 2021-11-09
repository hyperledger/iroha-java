//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String

/**
 * SetKeyValueBox
 *
 * Generated from 'iroha_data_model::isi::SetKeyValueBox' regular structure
 */
public data class SetKeyValueBox(
    public val objectId: EvaluatesTo<IdBox>,
    public val key: EvaluatesTo<String>,
    public val `value`: EvaluatesTo<Value>
) {
    public companion object : ScaleReader<SetKeyValueBox>, ScaleWriter<SetKeyValueBox> {
        public override fun read(reader: ScaleCodecReader): SetKeyValueBox = try {
            SetKeyValueBox(
                EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
                EvaluatesTo.read(reader) as EvaluatesTo<String>,
                EvaluatesTo.read(reader) as EvaluatesTo<Value>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SetKeyValueBox) = try {
            EvaluatesTo.write(writer, instance.objectId)
            EvaluatesTo.write(writer, instance.key)
            EvaluatesTo.write(writer, instance.`value`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
