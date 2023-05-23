//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any

/**
 * OriginFilter
 *
 * Generated from 'OriginFilter<TriggerEvent>' regular structure
 */
public data class OriginFilter<T0>(
    public val triggerId: TriggerId
) {
    public companion object : ScaleReader<OriginFilter<out Any>>, ScaleWriter<OriginFilter<out Any>> {
        public override fun read(reader: ScaleCodecReader): OriginFilter<out Any> = try {
            OriginFilter(
                TriggerId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: OriginFilter<out Any>) = try {
            TriggerId.write(writer, instance.triggerId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
