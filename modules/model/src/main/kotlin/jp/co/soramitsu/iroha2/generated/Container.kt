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
import kotlin.Int

/**
 * Container
 *
 * Generated from 'Container' enum
 */
public sealed class Container : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Any' variant
     */
    public data class Any(
        public val valuePredicate: ValuePredicate
    ) : Container() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Any>, ScaleWriter<Any> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Any = try {
                Any(
                    ValuePredicate.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Any) = try {
                ValuePredicate.write(writer, instance.valuePredicate)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'All' variant
     */
    public data class All(
        public val valuePredicate: ValuePredicate
    ) : Container() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<All>, ScaleWriter<All> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): All = try {
                All(
                    ValuePredicate.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: All) = try {
                ValuePredicate.write(writer, instance.valuePredicate)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'AtIndex' variant
     */
    public data class AtIndex(
        public val atIndex: jp.co.soramitsu.iroha2.generated.AtIndex
    ) : Container() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AtIndex>, ScaleWriter<AtIndex> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): AtIndex = try {
                AtIndex(
                    jp.co.soramitsu.iroha2.generated.AtIndex.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AtIndex) = try {
                jp.co.soramitsu.iroha2.generated.AtIndex.write(writer, instance.atIndex)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ValueOfKey' variant
     */
    public data class ValueOfKey(
        public val valueOfKey: jp.co.soramitsu.iroha2.generated.ValueOfKey
    ) : Container() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ValueOfKey>, ScaleWriter<ValueOfKey> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): ValueOfKey = try {
                ValueOfKey(
                    jp.co.soramitsu.iroha2.generated.ValueOfKey.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ValueOfKey) = try {
                jp.co.soramitsu.iroha2.generated.ValueOfKey.write(writer, instance.valueOfKey)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'HasKey' variant
     */
    public data class HasKey(
        public val name: Name
    ) : Container() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<HasKey>, ScaleWriter<HasKey> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): HasKey = try {
                HasKey(
                    Name.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: HasKey) = try {
                Name.write(writer, instance.name)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Container>, ScaleWriter<Container> {
        public override fun read(reader: ScaleCodecReader): Container = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Any.read(reader)
            1 -> All.read(reader)
            2 -> AtIndex.read(reader)
            3 -> ValueOfKey.read(reader)
            4 -> HasKey.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Container) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Any.write(writer, instance as Any)
                1 -> All.write(writer, instance as All)
                2 -> AtIndex.write(writer, instance as AtIndex)
                3 -> ValueOfKey.write(writer, instance as ValueOfKey)
                4 -> HasKey.write(writer, instance as HasKey)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
