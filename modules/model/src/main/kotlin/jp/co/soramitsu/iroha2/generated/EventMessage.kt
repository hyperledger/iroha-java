//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * EventMessage
 *
 * Generated from 'EventMessage' regular structure
 */
public data class EventMessage(
    public val event: Event
) {
    public companion object : ScaleReader<EventMessage>, ScaleWriter<EventMessage> {
        public override fun read(reader: ScaleCodecReader): EventMessage = try {
            EventMessage(
                Event.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: EventMessage) = try {
            Event.write(writer, instance.event)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
