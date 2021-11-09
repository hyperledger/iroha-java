//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * UnregisterBox
 *
 * Generated from 'iroha_data_model::isi::UnregisterBox' regular structure
 */
public data class UnregisterBox(
    public val objectId: EvaluatesTo<IdBox>
) {
    public companion object : ScaleReader<UnregisterBox>, ScaleWriter<UnregisterBox> {
        public override fun read(reader: ScaleCodecReader): UnregisterBox = try {
            UnregisterBox(
                EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: UnregisterBox) = try {
            EvaluatesTo.write(writer, instance.objectId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
