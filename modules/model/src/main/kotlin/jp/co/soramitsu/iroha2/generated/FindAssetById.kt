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
 * FindAssetById
 *
 * Generated from 'FindAssetById' regular structure
 */
public data class FindAssetById(
    public val id: EvaluatesTo<AssetId>,
) {
    public companion object : ScaleReader<FindAssetById>, ScaleWriter<FindAssetById> {
        override fun read(reader: ScaleCodecReader): FindAssetById = try {
            FindAssetById(
                EvaluatesTo.read(reader) as EvaluatesTo<AssetId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: FindAssetById): Unit = try {
            EvaluatesTo.write(writer, instance.id)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
