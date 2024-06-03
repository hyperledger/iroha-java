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
 * ValueOfKey
 *
 * Generated from 'ValueOfKey' regular structure
 */
public data class ValueOfKey(
    public val key: Name,
    public val predicate: ValuePredicate,
) {
    public companion object : ScaleReader<ValueOfKey>, ScaleWriter<ValueOfKey> {
        override fun read(reader: ScaleCodecReader): ValueOfKey = try {
            ValueOfKey(
                Name.read(reader),
                ValuePredicate.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: ValueOfKey): Unit = try {
            Name.write(writer, instance.key)
            ValuePredicate.write(writer, instance.predicate)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
