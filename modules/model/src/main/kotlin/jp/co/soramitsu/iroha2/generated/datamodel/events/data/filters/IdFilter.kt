//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any

/**
 * IdFilter
 *
 * Generated from 'iroha_data_model::events::data::filters::IdFilter<iroha_data_model::trigger::Id>'
 * tuple structure
 */
public data class IdFilter<T0>(
    public val id: Id
) {
    public companion object : ScaleReader<IdFilter<out Any>>, ScaleWriter<IdFilter<out Any>> {
        public override fun read(reader: ScaleCodecReader): IdFilter<out Any> = try {
            IdFilter(
                Id.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: IdFilter<out Any>) = try {
            Id.write(writer, instance.id)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
