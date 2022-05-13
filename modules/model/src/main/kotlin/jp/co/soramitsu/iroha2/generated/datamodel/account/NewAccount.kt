//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.account

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.comparator
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List

/**
 * NewAccount
 *
 * Generated from 'iroha_data_model::account::NewAccount' regular structure
 */
public data class NewAccount(
    public val id: Id,
    public val signatories: List<PublicKey>,
    public val metadata: Metadata
) {
    public companion object : ScaleReader<NewAccount>, ScaleWriter<NewAccount> {
        public override fun read(reader: ScaleCodecReader): NewAccount = try {
            NewAccount(
                Id.read(reader),
                reader.readVec(reader.readCompactInt()) { PublicKey.read(reader) },
                Metadata.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: NewAccount) = try {
            Id.write(writer, instance.id)
            writer.writeCompact(instance.signatories.size)
            instance.signatories.sortedWith(
                PublicKey::class.comparator()
            ).forEach { value ->
                PublicKey.write(writer, value)
            }
            Metadata.write(writer, instance.metadata)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
