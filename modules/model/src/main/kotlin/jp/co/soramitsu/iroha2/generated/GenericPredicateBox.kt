//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.Int

/**
 * GenericPredicateBox
 *
 * Generated from 'GenericPredicateBox<ValuePredicate>' enum
 */
public sealed class GenericPredicateBox<T0> : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'And' variant
     */
    public data class And(
        public val nonTrivial: NonTrivial<GenericPredicateBox<ValuePredicate>>
    ) : GenericPredicateBox<ValuePredicate>() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<And>, ScaleWriter<And> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): And = try {
                And(
                    NonTrivial.read(reader) as NonTrivial<GenericPredicateBox<ValuePredicate>>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: And) = try {
                NonTrivial.write(writer, instance.nonTrivial)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Or' variant
     */
    public data class Or(
        public val nonTrivial: NonTrivial<GenericPredicateBox<ValuePredicate>>
    ) : GenericPredicateBox<ValuePredicate>() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Or>, ScaleWriter<Or> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Or = try {
                Or(
                    NonTrivial.read(reader) as NonTrivial<GenericPredicateBox<ValuePredicate>>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Or) = try {
                NonTrivial.write(writer, instance.nonTrivial)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Not' variant
     */
    public data class Not(
        public val genericPredicateBox: GenericPredicateBox<ValuePredicate>
    ) : GenericPredicateBox<ValuePredicate>() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Not>, ScaleWriter<Not> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Not = try {
                Not(
                    GenericPredicateBox.read(reader) as GenericPredicateBox<ValuePredicate>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Not) = try {
                GenericPredicateBox.write(writer, instance.genericPredicateBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Raw' variant
     */
    public data class Raw(
        public val valuePredicate: ValuePredicate
    ) : GenericPredicateBox<ValuePredicate>() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Raw>, ScaleWriter<Raw> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Raw = try {
                Raw(
                    ValuePredicate.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Raw) = try {
                ValuePredicate.write(writer, instance.valuePredicate)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object :
        ScaleReader<GenericPredicateBox<out Any>>,
        ScaleWriter<GenericPredicateBox<out Any>> {
        public override fun read(reader: ScaleCodecReader): GenericPredicateBox<out Any> = when (
            val
            discriminant = reader.readUByte()
        ) {
            0 -> And.read(reader)
            1 -> Or.read(reader)
            2 -> Not.read(reader)
            3 -> Raw.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: GenericPredicateBox<out Any>) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> And.write(writer, instance as And)
                1 -> Or.write(writer, instance as Or)
                2 -> Not.write(writer, instance as Not)
                3 -> Raw.write(writer, instance as Raw)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
