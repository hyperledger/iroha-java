//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.predicate.nontrivial

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.GenericValuePredicateBox
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.`value`.ValuePredicate
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.collections.List

/**
 * NonTrivial
 *
 * Generated from
 * 'iroha_data_model::predicate::nontrivial::NonTrivial<iroha_data_model::predicate::GenericValuePredicateBox<iroha_data_model::predicate::value::ValuePredicate>>'
 * tuple structure
 */
public data class NonTrivial<T0>(
    public val vec: List<GenericValuePredicateBox<ValuePredicate>>
) {
    public companion object : ScaleReader<NonTrivial<out Any>>, ScaleWriter<NonTrivial<out Any>> {
        public override fun read(reader: ScaleCodecReader): NonTrivial<out Any> = try {
            NonTrivial(
                reader.readVec(reader.readCompactInt()) {
                    GenericValuePredicateBox.read(reader) as
                        GenericValuePredicateBox<ValuePredicate>
                },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: NonTrivial<out Any>) = try {
            writer.writeCompact(instance.vec.size)
            instance.vec.forEach { value ->
                GenericValuePredicateBox.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
