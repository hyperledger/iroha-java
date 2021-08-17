//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.String

/**
 * UnsatisfiedSignatureConditionFail
 *
 * Generated from 'iroha_data_model::events::pipeline::UnsatisfiedSignatureConditionFail' regular
 * structure
 */
public class UnsatisfiedSignatureConditionFail(
    public val reason: String
) {
    public companion object :
        ScaleReader<UnsatisfiedSignatureConditionFail>,
        ScaleWriter<UnsatisfiedSignatureConditionFail> {
        public override fun read(reader: ScaleCodecReader): UnsatisfiedSignatureConditionFail =
            UnsatisfiedSignatureConditionFail(
                reader.readString(),
            )

        public override fun write(
            writer: ScaleCodecWriter,
            instance: UnsatisfiedSignatureConditionFail
        ) {
            writer.writeAsList(instance.reason.toByteArray(Charsets.UTF_8))
        }
    }
}
