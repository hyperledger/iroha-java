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
 * Mod
 *
 * Generated from 'Mod' regular structure
 */
public data class Mod(
    public val left: EvaluatesTo<NumericValue>,
    public val right: EvaluatesTo<NumericValue>
) {
    public companion object : ScaleReader<Mod>, ScaleWriter<Mod> {
        public override fun read(reader: ScaleCodecReader): Mod = try {
            Mod(
                EvaluatesTo.read(reader) as EvaluatesTo<NumericValue>,
                EvaluatesTo.read(reader) as EvaluatesTo<NumericValue>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Mod) = try {
            EvaluatesTo.write(writer, instance.left)
            EvaluatesTo.write(writer, instance.right)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
