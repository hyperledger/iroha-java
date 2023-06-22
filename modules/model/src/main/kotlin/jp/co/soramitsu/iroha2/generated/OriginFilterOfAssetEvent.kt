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
 * OriginFilterOfAssetEvent
 *
 * Generated from 'OriginFilterOfAssetEvent' regular structure
 */
public data class OriginFilterOfAssetEvent(
    public val assetId: AssetId
) {
    public companion object :
        ScaleReader<OriginFilterOfAssetEvent>,
        ScaleWriter<OriginFilterOfAssetEvent> {
        public override fun read(reader: ScaleCodecReader): OriginFilterOfAssetEvent = try {
            OriginFilterOfAssetEvent(
                AssetId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: OriginFilterOfAssetEvent) = try {
            AssetId.write(writer, instance.assetId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
