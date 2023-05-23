//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List

/**
 * NewAccount
 *
 * Generated from 'NewAccount' regular structure
 */
public data class NewAccount(
    public val id: AccountId,
    public val signatories: List<PublicKey>,
    public val metadata: Metadata
) {
    public companion object : ScaleReader<NewAccount>, ScaleWriter<NewAccount> {
        public override fun read(reader: ScaleCodecReader): NewAccount = try {
            NewAccount(
                AccountId.read(reader),
                reader.readVec(reader.readCompactInt()) { PublicKey.read(reader) },
                Metadata.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: NewAccount) = try {
            AccountId.write(writer, instance.id)
            writer.writeCompact(instance.signatories.size)
            instance.signatories.forEach { value ->
                PublicKey.write(writer, value)
            }
            Metadata.write(writer, instance.metadata)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
