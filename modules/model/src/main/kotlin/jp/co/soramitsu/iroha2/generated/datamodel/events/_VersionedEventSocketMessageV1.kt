//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * _VersionedEventSocketMessageV1
 *
 * Generated from 'iroha_data_model::events::_VersionedEventSocketMessageV1' tuple structure
 */
public data class _VersionedEventSocketMessageV1(
    public val eventSocketMessage: EventSocketMessage
) {
    public companion object :
        ScaleReader<_VersionedEventSocketMessageV1>,
        ScaleWriter<_VersionedEventSocketMessageV1> {
        public override fun read(reader: ScaleCodecReader): _VersionedEventSocketMessageV1 = try {
            _VersionedEventSocketMessageV1(
                EventSocketMessage.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: _VersionedEventSocketMessageV1) =
            try {
                EventSocketMessage.write(writer, instance.eventSocketMessage)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
