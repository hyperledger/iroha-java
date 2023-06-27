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
 * FindTotalAssetQuantityByAssetDefinitionId
 *
 * Generated from 'FindTotalAssetQuantityByAssetDefinitionId' regular structure
 */
public data class FindTotalAssetQuantityByAssetDefinitionId(
    public val id: EvaluatesTo<AssetDefinitionId>
) {
    public companion object :
        ScaleReader<FindTotalAssetQuantityByAssetDefinitionId>,
        ScaleWriter<FindTotalAssetQuantityByAssetDefinitionId> {
        public override fun read(reader: ScaleCodecReader): FindTotalAssetQuantityByAssetDefinitionId =
            try {
                FindTotalAssetQuantityByAssetDefinitionId(
                    EvaluatesTo.read(reader) as EvaluatesTo<AssetDefinitionId>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

        public override fun write(
            writer: ScaleCodecWriter,
            instance: FindTotalAssetQuantityByAssetDefinitionId
        ) = try {
            EvaluatesTo.write(writer, instance.id)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
