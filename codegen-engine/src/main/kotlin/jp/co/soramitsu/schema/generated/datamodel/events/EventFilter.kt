// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.events

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
    private val pipeline: jp.co.soramitsu.schema.generated.datamodel.events.pipeline.EventFilter
  ) : EventFilter() {
    public override fun discriminant(): Int = 0

    public companion object CODEC : ScaleReader<Pipeline>, ScaleWriter<Pipeline> {
      public override fun read(reader: ScaleCodecReader): Pipeline =
          jp.co.soramitsu.schema.generated.datamodel.events.EventFilter.Pipeline(jp.co.soramitsu.schema.generated.datamodel.events.pipeline.EventFilter.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Pipeline): Unit {
        jp.co.soramitsu.schema.generated.datamodel.events.pipeline.EventFilter.write(writer,
            instance.`pipeline`)
      }
    }
  }

  /**
   * 'Data' variant
   */
  public class Data(
    private val `data`: jp.co.soramitsu.schema.generated.datamodel.events.`data`.EventFilter
  ) : EventFilter() {
    public override fun discriminant(): Int = 1

    public companion object CODEC : ScaleReader<Data>, ScaleWriter<Data> {
      public override fun read(reader: ScaleCodecReader): Data =
          jp.co.soramitsu.schema.generated.datamodel.events.EventFilter.Data(jp.co.soramitsu.schema.generated.datamodel.events.data.EventFilter.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Data): Unit {
        jp.co.soramitsu.schema.generated.datamodel.events.data.EventFilter.write(writer,
            instance.`data`)
      }
    }
  }

  public companion object CODEC : ScaleReader<EventFilter>, ScaleWriter<EventFilter> {
    public override fun read(reader: ScaleCodecReader): EventFilter = when(reader.readUByte()) {
    	0 -> Pipeline.read(reader)
    	1 -> Data.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant")
    }

    public override fun write(writer: ScaleCodecWriter, instance: EventFilter): Unit {
      writer.directWrite(instance.discriminant())
      when(instance.discriminant()) {
      	0 -> Pipeline.write(writer, instance as Pipeline)
      	1 -> Data.write(writer, instance as Data)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant")
      }
    }
  }
}
