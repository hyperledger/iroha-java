//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Int

/**
 * Event
 *
 * Generated from 'iroha_data_model::events::Event' enum
 */
public sealed class Event {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Pipeline' variant
     */
    public data class Pipeline(
        public val event: jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.Event
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Pipeline>, ScaleWriter<Pipeline> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Pipeline = Pipeline(
                jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.Event.read(reader) as
                    jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.Event,
            )

            public override fun write(writer: ScaleCodecWriter, instance: Pipeline) {
                jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.Event.write(
                    writer,
                    instance.event
                )
            }
        }
    }

    /**
     * 'Data' variant
     */
    public data class Data(
        public val event: jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.Event
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Data>, ScaleWriter<Data> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Data = Data(
                jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.Event.read(reader) as
                    jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.Event,
            )

            public override fun write(writer: ScaleCodecWriter, instance: Data) {
                jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.Event.write(
                    writer,
                    instance.event
                )
            }
        }
    }

    public companion object : ScaleReader<Event>, ScaleWriter<Event> {
        public override fun read(reader: ScaleCodecReader): Event = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Pipeline.read(reader)
            1 -> Data.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Event) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Pipeline.write(writer, instance as Pipeline)
                1 -> Data.write(writer, instance as Data)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
