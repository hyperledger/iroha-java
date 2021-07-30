//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events

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
    public val _VersionedEventSocketMessageV1: _VersionedEventSocketMessageV1
  ) : VersionedEventSocketMessage() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<V1>, ScaleWriter<V1> {
      public const val DISCRIMINANT: Int = 1

      public override fun read(reader: ScaleCodecReader): V1 = V1(
        _VersionedEventSocketMessageV1.read(reader) as _VersionedEventSocketMessageV1,
      )

      public override fun write(writer: ScaleCodecWriter, instance: V1): Unit {
          _VersionedEventSocketMessageV1.write(writer, instance._VersionedEventSocketMessageV1)
      }
    }
  }

  public companion object : ScaleReader<VersionedEventSocketMessage>,
      ScaleWriter<VersionedEventSocketMessage> {
    public override fun read(reader: ScaleCodecReader): VersionedEventSocketMessage = when(val
        discriminant = reader.readUByte()) {
    	1 -> V1.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}

    public override fun write(writer: ScaleCodecWriter, instance: VersionedEventSocketMessage):
        Unit {
      writer.directWrite(instance.discriminant())
      when(val discriminant = instance.discriminant()) {
      	1 -> V1.write(writer, instance as V1)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}
    }
  }
}
