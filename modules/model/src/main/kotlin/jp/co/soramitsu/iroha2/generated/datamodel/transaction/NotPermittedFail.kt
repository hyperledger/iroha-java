//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String

/**
 * NotPermittedFail
 *
 * Generated from 'iroha_data_model::transaction::NotPermittedFail' regular structure
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
