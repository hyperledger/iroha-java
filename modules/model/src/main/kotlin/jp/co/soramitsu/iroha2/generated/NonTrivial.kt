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
import kotlin.collections.List

/**
 * NonTrivial
 *
 * Generated from 'NonTrivial<GenericPredicateBox<ValuePredicate>>' regular structure
 */
public data class NonTrivial<T0>(
    public val vecOfValuePredicate: List<GenericPredicateBox<ValuePredicate>>
) {
    public companion object : ScaleReader<NonTrivial<out Any>>, ScaleWriter<NonTrivial<out Any>> {
        public override fun read(reader: ScaleCodecReader): NonTrivial<out Any> = try {
            NonTrivial(
                reader.readVec(reader.readCompactInt()) {
                    GenericPredicateBox.read(reader) as
                        GenericPredicateBox<ValuePredicate>
                },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: NonTrivial<out Any>) = try {
            writer.writeCompact(instance.vecOfValuePredicate.size)
            instance.vecOfValuePredicate.forEach { value ->
                GenericPredicateBox.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
