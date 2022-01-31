//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.account

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Boolean

/**
 * SignatureCheckCondition
 *
 * Generated from 'iroha_data_model::account::SignatureCheckCondition' tuple structure
 */
public data class SignatureCheckCondition(
    public val evaluatesTo: EvaluatesTo<Boolean>
) {
    public companion object :
        ScaleReader<SignatureCheckCondition>,
        ScaleWriter<SignatureCheckCondition> {
        public override fun read(reader: ScaleCodecReader): SignatureCheckCondition = try {
            SignatureCheckCondition(
                EvaluatesTo.read(reader) as EvaluatesTo<Boolean>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SignatureCheckCondition) = try {
            EvaluatesTo.write(writer, instance.evaluatesTo)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
