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
 * IsAssetDefinitionOwner
 *
 * Generated from 'IsAssetDefinitionOwner' regular structure
 */
public data class IsAssetDefinitionOwner(
    public val assetDefinitionId: EvaluatesTo<AssetDefinitionId>,
    public val accountId: EvaluatesTo<AccountId>,
) {
    public companion object : ScaleReader<IsAssetDefinitionOwner>, ScaleWriter<IsAssetDefinitionOwner> {
        override fun read(reader: ScaleCodecReader): IsAssetDefinitionOwner = try {
            IsAssetDefinitionOwner(
                EvaluatesTo.read(reader) as EvaluatesTo<AssetDefinitionId>,
                EvaluatesTo.read(reader) as EvaluatesTo<AccountId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: IsAssetDefinitionOwner): Unit = try {
            EvaluatesTo.write(writer, instance.assetDefinitionId)
            EvaluatesTo.write(writer, instance.accountId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
