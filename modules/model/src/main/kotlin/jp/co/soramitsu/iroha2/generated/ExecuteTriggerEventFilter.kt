//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit

/**
 * ExecuteTriggerEventFilter
 *
 * Generated from 'ExecuteTriggerEventFilter' regular structure
 */
public data class ExecuteTriggerEventFilter(
    public val triggerId: TriggerId,
    public val authority: FilterOptOfAccountId,
) {
    public companion object :
        ScaleReader<ExecuteTriggerEventFilter>,
        ScaleWriter<ExecuteTriggerEventFilter> {
        override fun read(reader: ScaleCodecReader): ExecuteTriggerEventFilter = try {
            ExecuteTriggerEventFilter(
                TriggerId.read(reader),
                FilterOptOfAccountId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: ExecuteTriggerEventFilter): Unit = try {
            TriggerId.write(writer, instance.triggerId)
            FilterOptOfAccountId.write(writer, instance.authority)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
