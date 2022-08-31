//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.wrapException

/**
 * OriginFilterEventsDataEventsAssetAssetDefinitionEvent
 *
 * Generated from
 * 'iroha_data_model::events::data::filters::OriginFilterEventsDataEventsAssetAssetDefinitionEvent'
 * tuple structure
 */
public data class OriginFilterEventsDataEventsAssetAssetDefinitionEvent(
    public val definitionId: DefinitionId
) {
    public companion object :
        ScaleReader<OriginFilterEventsDataEventsAssetAssetDefinitionEvent>,
        ScaleWriter<OriginFilterEventsDataEventsAssetAssetDefinitionEvent> {
        public override fun read(reader: ScaleCodecReader):
            OriginFilterEventsDataEventsAssetAssetDefinitionEvent = try {
            OriginFilterEventsDataEventsAssetAssetDefinitionEvent(
                DefinitionId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(
            writer: ScaleCodecWriter,
            instance: OriginFilterEventsDataEventsAssetAssetDefinitionEvent
        ) = try {
            DefinitionId.write(writer, instance.definitionId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
