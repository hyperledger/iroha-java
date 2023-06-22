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
 * OriginFilterOfTriggerEvent
 *
 * Generated from 'OriginFilterOfTriggerEvent' regular structure
 */
public data class OriginFilterOfTriggerEvent(
    public val triggerId: TriggerId
) {
    public companion object :
        ScaleReader<OriginFilterOfTriggerEvent>,
        ScaleWriter<OriginFilterOfTriggerEvent> {
        public override fun read(reader: ScaleCodecReader): OriginFilterOfTriggerEvent = try {
            OriginFilterOfTriggerEvent(
                TriggerId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: OriginFilterOfTriggerEvent) = try {
            TriggerId.write(writer, instance.triggerId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
