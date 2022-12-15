//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.trigger

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.Boolean
import kotlin.Int

/**
 * FindAllActiveTriggerIds
 *
 * Generated from 'iroha_data_model::query::trigger::FindAllActiveTriggerIds' tuple structure
 */
public class FindAllActiveTriggerIds {
    public companion object :
        ScaleReader<FindAllActiveTriggerIds>,
        ScaleWriter<FindAllActiveTriggerIds> {
        public override fun read(reader: ScaleCodecReader): FindAllActiveTriggerIds = try {
            FindAllActiveTriggerIds()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAllActiveTriggerIds) = try {
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public fun equals(o1: FindAllActiveTriggerIds, o2: Any?): Boolean = when (o2) {
            null -> false
            else -> o2::class == o1::class
        }

        public override fun hashCode(): Int = 1
    }
}
