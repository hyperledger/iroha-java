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
 * RevokeBox
 *
 * Generated from 'RevokeBox' regular structure
 */
public data class RevokeBox(
    public val `object`: EvaluatesTo<Value>,
    public val destinationId: EvaluatesTo<IdBox>
) {
    public companion object : ScaleReader<RevokeBox>, ScaleWriter<RevokeBox> {
        public override fun read(reader: ScaleCodecReader): RevokeBox = try {
            RevokeBox(
                EvaluatesTo.read(reader) as EvaluatesTo<Value>,
                EvaluatesTo.read(reader) as EvaluatesTo<IdBox>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: RevokeBox) = try {
            EvaluatesTo.write(writer, instance.`object`)
            EvaluatesTo.write(writer, instance.destinationId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
