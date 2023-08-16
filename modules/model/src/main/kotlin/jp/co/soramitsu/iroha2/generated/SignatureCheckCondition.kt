//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Boolean
import kotlin.Unit

/**
 * SignatureCheckCondition
 *
 * Generated from 'SignatureCheckCondition' regular structure
 */
public data class SignatureCheckCondition(
    public val evaluatesToOfBool: EvaluatesTo<Boolean>,
) {
    public companion object :
        ScaleReader<SignatureCheckCondition>,
        ScaleWriter<SignatureCheckCondition> {
        override fun read(reader: ScaleCodecReader): SignatureCheckCondition = try {
            SignatureCheckCondition(
                EvaluatesTo.read(reader) as EvaluatesTo<Boolean>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: SignatureCheckCondition): Unit = try {
            EvaluatesTo.write(writer, instance.evaluatesToOfBool)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
