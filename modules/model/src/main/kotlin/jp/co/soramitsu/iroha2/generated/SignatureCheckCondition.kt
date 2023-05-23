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
 * SignatureCheckCondition
 *
 * Generated from 'SignatureCheckCondition' regular structure
 */
public data class SignatureCheckCondition(
    public val evaluatesToOfBool: EvaluatesTo<Bool>
) {
    public companion object :
        ScaleReader<SignatureCheckCondition>,
        ScaleWriter<SignatureCheckCondition> {
        public override fun read(reader: ScaleCodecReader): SignatureCheckCondition = try {
            SignatureCheckCondition(
                EvaluatesTo.read(reader) as EvaluatesTo<Bool>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SignatureCheckCondition) = try {
            EvaluatesTo.write(writer, instance.evaluatesToOfBool)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
