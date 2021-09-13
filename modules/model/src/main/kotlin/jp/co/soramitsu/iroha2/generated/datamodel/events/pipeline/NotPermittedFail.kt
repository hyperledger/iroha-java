//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String

/**
 * NotPermittedFail
 *
 * Generated from 'iroha_data_model::events::pipeline::NotPermittedFail' regular structure
 */
public data class NotPermittedFail(
    public val reason: String
) {
    public companion object : ScaleReader<NotPermittedFail>, ScaleWriter<NotPermittedFail> {
        public override fun read(reader: ScaleCodecReader): NotPermittedFail = try {
            NotPermittedFail(
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: NotPermittedFail) = try {
            writer.writeAsList(instance.reason.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
