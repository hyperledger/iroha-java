//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.wrapException

/**
 * OriginFilterEventsDataEventsAccountAccountEvent
 *
 * Generated from
 * 'iroha_data_model::events::data::filters::OriginFilterEventsDataEventsAccountAccountEvent' tuple
 * structure
 */
public data class OriginFilterEventsDataEventsAccountAccountEvent(
    public val accountId: AccountId
) {
    public companion object :
        ScaleReader<OriginFilterEventsDataEventsAccountAccountEvent>,
        ScaleWriter<OriginFilterEventsDataEventsAccountAccountEvent> {
        public override fun read(reader: ScaleCodecReader):
            OriginFilterEventsDataEventsAccountAccountEvent = try {
            OriginFilterEventsDataEventsAccountAccountEvent(
                AccountId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(
            writer: ScaleCodecWriter,
            instance: OriginFilterEventsDataEventsAccountAccountEvent
        ) = try {
            AccountId.write(writer, instance.accountId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
