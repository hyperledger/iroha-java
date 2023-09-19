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
 * MintBox
 *
 * Generated from 'MintBox' regular structure
 */
public data class MintBox(
    public val `object`: EvaluatesTo<Value>,
    public val destinationId: EvaluatesTo<IdBox>,
) {
    public companion object : ScaleReader<MintBox>, ScaleWriter<MintBox> {
        override fun read(reader: ScaleCodecReader): MintBox = try {
            MintBox(
                EvaluatesTo.read(reader) as EvaluatesTo<Value>,
                EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: MintBox): Unit = try {
            EvaluatesTo.write(writer, instance.`object`)
            EvaluatesTo.write(writer, instance.destinationId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
