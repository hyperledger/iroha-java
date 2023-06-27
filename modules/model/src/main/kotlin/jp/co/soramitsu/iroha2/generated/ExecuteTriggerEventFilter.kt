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
 * ExecuteTriggerEventFilter
 *
 * Generated from 'ExecuteTriggerEventFilter' regular structure
 */
public data class ExecuteTriggerEventFilter(
    public val triggerId: TriggerId,
    public val authority: AccountId
) {
    public companion object :
        ScaleReader<ExecuteTriggerEventFilter>,
        ScaleWriter<ExecuteTriggerEventFilter> {
        public override fun read(reader: ScaleCodecReader): ExecuteTriggerEventFilter = try {
            ExecuteTriggerEventFilter(
                TriggerId.read(reader),
                AccountId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: ExecuteTriggerEventFilter) = try {
            TriggerId.write(writer, instance.triggerId)
            AccountId.write(writer, instance.authority)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
