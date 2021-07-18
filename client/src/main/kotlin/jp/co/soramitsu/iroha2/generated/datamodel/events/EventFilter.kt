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
 * EventFilter
 *
 * Generated from 'iroha_data_model::events::EventFilter' enum
 */
public sealed class EventFilter {
  /**
   * @return Discriminator of variant in enum
   */
  public abstract fun discriminant(): Int

  /**
   * 'Pipeline' variant
   */
  public class Pipeline(
    private val eventFilter: jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.EventFilter
  ) : EventFilter() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Pipeline>, ScaleWriter<Pipeline> {
      public const val DISCRIMINANT: Int = 0

      public override fun read(reader: ScaleCodecReader): Pipeline =
          Pipeline(EventFilter.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Pipeline): Unit {
        EventFilter.write(writer, instance.eventFilter)
      }
    }
  }

  /**
   * 'Data' variant
   */
  public class Data(
    private val eventFilter: jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.EventFilter
  ) : EventFilter() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Data>, ScaleWriter<Data> {
      public const val DISCRIMINANT: Int = 1

      public override fun read(reader: ScaleCodecReader): Data = Data(EventFilter.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Data): Unit {
        EventFilter.write(writer, instance.eventFilter)
      }
    }
  }

  public companion object : ScaleReader<EventFilter>, ScaleWriter<EventFilter> {
    public override fun read(reader: ScaleCodecReader): EventFilter = when(val discriminant =
        reader.readUByte()) {
    	0 -> Pipeline.read(reader)
    	1 -> Data.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}

    public override fun write(writer: ScaleCodecWriter, instance: EventFilter): Unit {
      writer.directWrite(instance.discriminant())
      when(val discriminant = instance.discriminant()) {
      	0 -> Pipeline.write(writer, instance as Pipeline)
      	1 -> Data.write(writer, instance as Data)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}
    }
  }
}
