//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.trigger

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindTriggerKeyValueByIdAndKey
 *
 * Generated from 'iroha_data_model::query::trigger::FindTriggerKeyValueByIdAndKey' regular
 * structure
 */
public data class FindTriggerKeyValueByIdAndKey(
    public val id: EvaluatesTo<TriggerId>,
    public val key: EvaluatesTo<Name>
) {
    public companion object :
        ScaleReader<FindTriggerKeyValueByIdAndKey>,
        ScaleWriter<FindTriggerKeyValueByIdAndKey> {
        public override fun read(reader: ScaleCodecReader): FindTriggerKeyValueByIdAndKey = try {
            FindTriggerKeyValueByIdAndKey(
                EvaluatesTo.read(reader) as EvaluatesTo<TriggerId>,
                EvaluatesTo.read(reader) as EvaluatesTo<Name>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindTriggerKeyValueByIdAndKey) =
            try {
                EvaluatesTo.write(writer, instance.id)
                EvaluatesTo.write(writer, instance.key)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
