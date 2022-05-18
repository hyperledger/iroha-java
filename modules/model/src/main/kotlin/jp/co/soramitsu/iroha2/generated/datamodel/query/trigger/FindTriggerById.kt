//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.trigger

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindTriggerById
 *
 * Generated from 'iroha_data_model::query::trigger::FindTriggerById' regular structure
 */
public data class FindTriggerById(
    public val id: EvaluatesTo<Id>
) {
    public companion object : ScaleReader<FindTriggerById>, ScaleWriter<FindTriggerById> {
        public override fun read(reader: ScaleCodecReader): FindTriggerById = try {
            FindTriggerById(
                EvaluatesTo.read(reader) as EvaluatesTo<Id>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindTriggerById) = try {
            EvaluatesTo.write(writer, instance.id)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
