//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * MetadataChangedOfAccountId
 *
 * Generated from 'MetadataChangedOfAccountId' regular structure
 */
public data class MetadataChangedOfAccountId(
    public val targetId: AccountId,
    public val key: Name,
    public val `value`: Value
) {
    public companion object :
        ScaleReader<MetadataChangedOfAccountId>,
        ScaleWriter<MetadataChangedOfAccountId> {
        public override fun read(reader: ScaleCodecReader): MetadataChangedOfAccountId = try {
            MetadataChangedOfAccountId(
                AccountId.read(reader),
                Name.read(reader),
                Value.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: MetadataChangedOfAccountId) = try {
            AccountId.write(writer, instance.targetId)
            Name.write(writer, instance.key)
            Value.write(writer, instance.`value`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
