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
 * RemoveKeyValueBox
 *
 * Generated from 'RemoveKeyValueBox' regular structure
 */
public data class RemoveKeyValueBox(
    public val objectId: EvaluatesTo<IdBox>,
    public val key: EvaluatesTo<Name>,
) {
    public companion object : ScaleReader<RemoveKeyValueBox>, ScaleWriter<RemoveKeyValueBox> {
        override fun read(reader: ScaleCodecReader): RemoveKeyValueBox = try {
            RemoveKeyValueBox(
                EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
                EvaluatesTo.read(reader) as EvaluatesTo<Name>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: RemoveKeyValueBox): Unit = try {
            EvaluatesTo.write(writer, instance.objectId)
            EvaluatesTo.write(writer, instance.key)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
