//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String

/**
 * FailBox
 *
 * Generated from 'iroha_data_model::isi::FailBox' regular structure
 */
public data class FailBox(
    public val message: String
) {
    public companion object : ScaleReader<FailBox>, ScaleWriter<FailBox> {
        public override fun read(reader: ScaleCodecReader): FailBox = try {
            FailBox(
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FailBox) = try {
            writer.writeAsList(instance.message.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
