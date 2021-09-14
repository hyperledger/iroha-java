//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String

/**
 * RemoveKeyValueBox
 *
 * Generated from 'iroha_data_model::isi::RemoveKeyValueBox' regular structure
 */
public data class RemoveKeyValueBox(
    public val objectId: EvaluatesTo<IdBox>,
    public val key: EvaluatesTo<String>
) {
    public companion object : ScaleReader<RemoveKeyValueBox>, ScaleWriter<RemoveKeyValueBox> {
        public override fun read(reader: ScaleCodecReader): RemoveKeyValueBox = try {
            RemoveKeyValueBox(
                EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
                EvaluatesTo.read(reader) as EvaluatesTo<String>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: RemoveKeyValueBox) = try {
            EvaluatesTo.write(writer, instance.objectId)
            EvaluatesTo.write(writer, instance.key)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
