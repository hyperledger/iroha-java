//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Unit

/**
 * _VersionedEventSocketMessageV1
 *
 * Generated from 'iroha_data_model::events::_VersionedEventSocketMessageV1' tuple structure
 */
public data class _VersionedEventSocketMessageV1(
  public val eventSocketMessage: EventSocketMessage
) {
  public companion object : ScaleReader<_VersionedEventSocketMessageV1>,
      ScaleWriter<_VersionedEventSocketMessageV1> {
    public override fun read(reader: ScaleCodecReader): _VersionedEventSocketMessageV1 =
        _VersionedEventSocketMessageV1(
      EventSocketMessage.read(reader) as EventSocketMessage,
    )

    public override fun write(writer: ScaleCodecWriter, instance: _VersionedEventSocketMessageV1):
        Unit {
        EventSocketMessage.write(writer, instance.eventSocketMessage)
    }
  }
}
