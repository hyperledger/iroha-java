//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId
import jp.co.soramitsu.iroha2.wrapException

/**
 * OriginFilterEventsDataEventsDomainDomainEvent
 *
 * Generated from
 * 'iroha_data_model::events::data::filters::OriginFilterEventsDataEventsDomainDomainEvent' tuple
 * structure
 */
public data class OriginFilterEventsDataEventsDomainDomainEvent(
    public val domainId: DomainId
) {
    public companion object :
        ScaleReader<OriginFilterEventsDataEventsDomainDomainEvent>,
        ScaleWriter<OriginFilterEventsDataEventsDomainDomainEvent> {
        public override fun read(reader: ScaleCodecReader):
            OriginFilterEventsDataEventsDomainDomainEvent = try {
            OriginFilterEventsDataEventsDomainDomainEvent(
                DomainId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(
            writer: ScaleCodecWriter,
            instance: OriginFilterEventsDataEventsDomainDomainEvent
        ) = try {
            DomainId.write(writer, instance.domainId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
