//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int
import kotlin.Unit

/**
 * NotificationEvent
 *
 * Generated from 'NotificationEvent' enum
 */
public sealed class NotificationEvent : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'TriggerCompleted' variant
     */
    public data class TriggerCompleted(
        public val triggerCompletedEvent: TriggerCompletedEvent,
    ) : NotificationEvent() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.NotificationEvent.TriggerCompleted>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.NotificationEvent.TriggerCompleted> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.NotificationEvent.TriggerCompleted = try {
                TriggerCompleted(
                    TriggerCompletedEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.NotificationEvent.TriggerCompleted,
            ): Unit = try {
                TriggerCompletedEvent.write(writer, instance.triggerCompletedEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<NotificationEvent>, ScaleWriter<NotificationEvent> {
        override fun read(reader: ScaleCodecReader): NotificationEvent = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> TriggerCompleted.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: NotificationEvent) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> TriggerCompleted.write(writer, instance as TriggerCompleted)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
