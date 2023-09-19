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
import kotlin.Unit

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
        public val valuePredicate: ValuePredicate,
    ) : Container() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Container.Any>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Container.Any> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Container.Any =
                try {
                    Any(
                        ValuePredicate.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Container.Any,
            ): Unit = try {
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
        public val valuePredicate: ValuePredicate,
    ) : Container() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Container.All>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Container.All> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Container.All =
                try {
                    All(
                        ValuePredicate.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Container.All,
            ): Unit = try {
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
        public val atIndex: jp.co.soramitsu.iroha2.generated.AtIndex,
    ) : Container() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Container.AtIndex>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Container.AtIndex> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Container.AtIndex = try {
                AtIndex(
                    jp.co.soramitsu.iroha2.generated.AtIndex.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Container.AtIndex,
            ): Unit = try {
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
        public val valueOfKey: jp.co.soramitsu.iroha2.generated.ValueOfKey,
    ) : Container() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Container.ValueOfKey>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Container.ValueOfKey> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Container.ValueOfKey = try {
                ValueOfKey(
                    jp.co.soramitsu.iroha2.generated.ValueOfKey.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Container.ValueOfKey,
            ): Unit = try {
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
        public val name: Name,
    ) : Container() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Container.HasKey>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Container.HasKey> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Container.HasKey = try {
                HasKey(
                    Name.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Container.HasKey,
            ): Unit = try {
                Name.write(writer, instance.name)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Container>, ScaleWriter<Container> {
        override fun read(reader: ScaleCodecReader): Container = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Any.read(reader)
            1 -> All.read(reader)
            2 -> AtIndex.read(reader)
            3 -> ValueOfKey.read(reader)
            4 -> HasKey.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: Container) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Any.write(writer, instance as Any)
                1 -> All.write(writer, instance as All)
                2 -> AtIndex.write(writer, instance as AtIndex)
                3 -> ValueOfKey.write(writer, instance as ValueOfKey)
                4 -> HasKey.write(writer, instance as HasKey)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
