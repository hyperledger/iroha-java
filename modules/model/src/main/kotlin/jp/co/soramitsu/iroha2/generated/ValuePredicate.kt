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
import kotlin.Boolean
import kotlin.Int
import kotlin.Unit

/**
 * ValuePredicate
 *
 * Generated from 'ValuePredicate' enum
 */
public sealed class ValuePredicate : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    override fun equals(other: Any?): Boolean = when (this) {
        is Pass -> Pass.equals(this, other)
        else -> super.equals(other) }

    override fun hashCode(): Int = when (this) {
        is Pass -> Pass.hashCode()
        else -> super.hashCode() }

    /**
     * 'Identifiable' variant
     */
    public data class Identifiable(
        public val stringPredicate: StringPredicate,
    ) : ValuePredicate() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ValuePredicate.Identifiable>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ValuePredicate.Identifiable> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ValuePredicate.Identifiable = try {
                Identifiable(
                    StringPredicate.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ValuePredicate.Identifiable,
            ): Unit = try {
                StringPredicate.write(writer, instance.stringPredicate)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Container' variant
     */
    public data class Container(
        public val container: jp.co.soramitsu.iroha2.generated.Container,
    ) : ValuePredicate() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ValuePredicate.Container>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ValuePredicate.Container> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ValuePredicate.Container = try {
                Container(
                    jp.co.soramitsu.iroha2.generated.Container.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ValuePredicate.Container,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Container.write(writer, instance.container)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Display' variant
     */
    public data class Display(
        public val stringPredicate: StringPredicate,
    ) : ValuePredicate() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ValuePredicate.Display>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ValuePredicate.Display> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ValuePredicate.Display = try {
                Display(
                    StringPredicate.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ValuePredicate.Display,
            ): Unit = try {
                StringPredicate.write(writer, instance.stringPredicate)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Numerical' variant
     */
    public data class Numerical(
        public val semiRange: SemiRange,
    ) : ValuePredicate() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ValuePredicate.Numerical>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ValuePredicate.Numerical> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ValuePredicate.Numerical = try {
                Numerical(
                    SemiRange.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ValuePredicate.Numerical,
            ): Unit = try {
                SemiRange.write(writer, instance.semiRange)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'TimeStamp' variant
     */
    public data class TimeStamp(
        public val semiIntervalOfu128: SemiIntervalOfu128,
    ) : ValuePredicate() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ValuePredicate.TimeStamp>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ValuePredicate.TimeStamp> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ValuePredicate.TimeStamp = try {
                TimeStamp(
                    SemiIntervalOfu128.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ValuePredicate.TimeStamp,
            ): Unit = try {
                SemiIntervalOfu128.write(writer, instance.semiIntervalOfu128)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Ipv4Addr' variant
     */
    public data class Ipv4Addr(
        public val ipv4Predicate: Ipv4Predicate,
    ) : ValuePredicate() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ValuePredicate.Ipv4Addr>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ValuePredicate.Ipv4Addr> {
            public const val DISCRIMINANT: Int = 5

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ValuePredicate.Ipv4Addr = try {
                Ipv4Addr(
                    Ipv4Predicate.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ValuePredicate.Ipv4Addr,
            ): Unit = try {
                Ipv4Predicate.write(writer, instance.ipv4Predicate)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Ipv6Addr' variant
     */
    public data class Ipv6Addr(
        public val ipv6Predicate: Ipv6Predicate,
    ) : ValuePredicate() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ValuePredicate.Ipv6Addr>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ValuePredicate.Ipv6Addr> {
            public const val DISCRIMINANT: Int = 6

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ValuePredicate.Ipv6Addr = try {
                Ipv6Addr(
                    Ipv6Predicate.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ValuePredicate.Ipv6Addr,
            ): Unit = try {
                Ipv6Predicate.write(writer, instance.ipv6Predicate)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Pass' variant
     */
    public class Pass : ValuePredicate() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.ValuePredicate.Pass>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.ValuePredicate.Pass> {
            public const val DISCRIMINANT: Int = 7

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.ValuePredicate.Pass = try {
                Pass()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.ValuePredicate.Pass,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: jp.co.soramitsu.iroha2.generated.ValuePredicate.Pass, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".ValuePredicate.Pass".hashCode()
        }
    }

    public companion object : ScaleReader<ValuePredicate>, ScaleWriter<ValuePredicate> {
        override fun read(reader: ScaleCodecReader): ValuePredicate = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Identifiable.read(reader)
            1 -> Container.read(reader)
            2 -> Display.read(reader)
            3 -> Numerical.read(reader)
            4 -> TimeStamp.read(reader)
            5 -> Ipv4Addr.read(reader)
            6 -> Ipv6Addr.read(reader)
            7 -> Pass.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: ValuePredicate) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Identifiable.write(writer, instance as Identifiable)
                1 -> Container.write(writer, instance as Container)
                2 -> Display.write(writer, instance as Display)
                3 -> Numerical.write(writer, instance as Numerical)
                4 -> TimeStamp.write(writer, instance as TimeStamp)
                5 -> Ipv4Addr.write(writer, instance as Ipv4Addr)
                6 -> Ipv6Addr.write(writer, instance as Ipv6Addr)
                7 -> Pass.write(writer, instance as Pass)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
