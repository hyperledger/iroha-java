//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit

/**
 * TransferBox
 *
 * Generated from 'TransferBox' regular structure
 */
public data class TransferBox(
    public val sourceId: EvaluatesTo<IdBox>,
    public val `object`: EvaluatesTo<Value>,
    public val destinationId: EvaluatesTo<IdBox>,
) {
    public companion object : ScaleReader<TransferBox>, ScaleWriter<TransferBox> {
        override fun read(reader: ScaleCodecReader): TransferBox = try {
            TransferBox(
                EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
                EvaluatesTo.read(reader) as EvaluatesTo<Value>,
                EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: TransferBox): Unit = try {
            EvaluatesTo.write(writer, instance.sourceId)
            EvaluatesTo.write(writer, instance.`object`)
            EvaluatesTo.write(writer, instance.destinationId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
