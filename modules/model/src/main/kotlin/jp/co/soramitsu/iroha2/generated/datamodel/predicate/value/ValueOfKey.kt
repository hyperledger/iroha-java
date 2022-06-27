//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.predicate.`value`

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.wrapException

/**
 * ValueOfKey
 *
 * Generated from 'iroha_data_model::predicate::value::ValueOfKey' regular structure
 */
public data class ValueOfKey(
    public val key: Name,
    public val predicate: Predicate
) {
    public companion object : ScaleReader<ValueOfKey>, ScaleWriter<ValueOfKey> {
        public override fun read(reader: ScaleCodecReader): ValueOfKey = try {
            ValueOfKey(
                Name.read(reader),
                Predicate.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: ValueOfKey) = try {
            Name.write(writer, instance.key)
            Predicate.write(writer, instance.predicate)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
