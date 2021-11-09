//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.account

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String

/**
 * Id
 *
 * Generated from 'iroha_data_model::account::Id' regular structure
 */
public data class Id(
    public val name: String,
    public val domainName: String
) {
    public companion object : ScaleReader<Id>, ScaleWriter<Id> {
        public override fun read(reader: ScaleCodecReader): Id = try {
            Id(
                reader.readString(),
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Id) = try {
            writer.writeAsList(instance.name.toByteArray(Charsets.UTF_8))
            writer.writeAsList(instance.domainName.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
