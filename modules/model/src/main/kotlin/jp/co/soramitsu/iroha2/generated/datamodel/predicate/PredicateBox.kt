//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.predicate

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.`value`.Predicate
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int
import kotlin.collections.List

/**
 * PredicateBox
 *
 * Generated from 'iroha_data_model::predicate::PredicateBox' enum
 */
public sealed class PredicateBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'And' variant
     */
    public data class And(
        public val vec: List<PredicateBox>
    ) : PredicateBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<And>, ScaleWriter<And> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): And = try {
                And(
                    reader.readVec(reader.readCompactInt()) { PredicateBox.read(reader) },
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: And) = try {
                writer.writeCompact(instance.vec.size)
                instance.vec.forEach { value ->
                    PredicateBox.write(writer, value)
                }
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Or' variant
     */
    public data class Or(
        public val vec: List<PredicateBox>
    ) : PredicateBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Or>, ScaleWriter<Or> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Or = try {
                Or(
                    reader.readVec(reader.readCompactInt()) { PredicateBox.read(reader) },
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Or) = try {
                writer.writeCompact(instance.vec.size)
                instance.vec.forEach { value ->
                    PredicateBox.write(writer, value)
                }
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Not' variant
     */
    public data class Not(
        public val predicateBox: PredicateBox
    ) : PredicateBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Not>, ScaleWriter<Not> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Not = try {
                Not(
                    PredicateBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Not) = try {
                PredicateBox.write(writer, instance.predicateBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Raw' variant
     */
    public data class Raw(
        public val predicate: Predicate
    ) : PredicateBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Raw>, ScaleWriter<Raw> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Raw = try {
                Raw(
                    Predicate.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Raw) = try {
                Predicate.write(writer, instance.predicate)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<PredicateBox>, ScaleWriter<PredicateBox> {
        public override fun read(reader: ScaleCodecReader): PredicateBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> And.read(reader)
            1 -> Or.read(reader)
            2 -> Not.read(reader)
            3 -> Raw.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: PredicateBox) {
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
