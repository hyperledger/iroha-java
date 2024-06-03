//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit

/**
 * AssetId
 *
 * Generated from 'AssetId' regular structure
 */
public data class AssetId(
    public val definitionId: AssetDefinitionId,
    public val accountId: AccountId,
) {
    public companion object : ScaleReader<AssetId>, ScaleWriter<AssetId> {
        override fun read(reader: ScaleCodecReader): AssetId = try {
            AssetId(
                AssetDefinitionId.read(reader),
                AccountId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: AssetId): Unit = try {
            AssetDefinitionId.write(writer, instance.definitionId)
            AccountId.write(writer, instance.accountId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
