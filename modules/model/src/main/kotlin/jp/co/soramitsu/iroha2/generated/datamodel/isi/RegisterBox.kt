//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.wrapException

/**
 * RegisterBox
 *
 * Generated from 'iroha_data_model::isi::RegisterBox' regular structure
 */
public data class RegisterBox(
    public val `object`: EvaluatesTo<IdentifiableBox>
) {
    public companion object : ScaleReader<RegisterBox>, ScaleWriter<RegisterBox> {
        public override fun read(reader: ScaleCodecReader): RegisterBox = try {
            RegisterBox(
                EvaluatesTo.read(reader) as EvaluatesTo<IdentifiableBox>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: RegisterBox) = try {
            EvaluatesTo.write(writer, instance.`object`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
