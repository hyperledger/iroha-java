// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.events

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Int
import kotlin.Unit

/**
 * VersionedEventSocketMessage
 *
 * Generated from 'iroha_data_model::events::VersionedEventSocketMessage' enum
 */
public sealed class VersionedEventSocketMessage {
  /**
   * @return Discriminator of variant in enum
   */
  public abstract fun discriminant(): Int

  /**
   * 'V1' variant
   */
  public class V1(
    private val v1: _VersionedEventSocketMessageV1
  ) : VersionedEventSocketMessage() {
    public override fun discriminant(): Int = 0

    public companion object CODEC : ScaleReader<V1>, ScaleWriter<V1> {
      public override fun read(reader: ScaleCodecReader): V1 =
          jp.co.soramitsu.schema.generated.datamodel.events.VersionedEventSocketMessage.V1(jp.co.soramitsu.schema.generated.datamodel.events._VersionedEventSocketMessageV1.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: V1): Unit {
        jp.co.soramitsu.schema.generated.datamodel.events._VersionedEventSocketMessageV1.write(writer,
            instance.v1)
      }
    }
  }

  public companion object CODEC : ScaleReader<VersionedEventSocketMessage>,
      ScaleWriter<VersionedEventSocketMessage> {
    public override fun read(reader: ScaleCodecReader): VersionedEventSocketMessage =
        when(reader.readUByte()) {
    	0 -> V1.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant")
    }

    public override fun write(writer: ScaleCodecWriter, instance: VersionedEventSocketMessage):
        Unit {
      when(instance.discriminant()) {
      	0 -> V1.write(writer, instance as V1)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant")
      }
    }
  }
}