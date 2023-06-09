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

    public override fun equals(other: Any?) = when (this) {
        is Pass -> Pass.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is Pass -> Pass.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'Identifiable' variant
     */
    public data class Identifiable(
        public val stringPredicate: StringPredicate
    ) : ValuePredicate() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Identifiable>, ScaleWriter<Identifiable> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Identifiable = try {
                Identifiable(
                    StringPredicate.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Identifiable) = try {
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
        public val container: jp.co.soramitsu.iroha2.generated.Container
    ) : ValuePredicate() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Container>, ScaleWriter<Container> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Container = try {
                Container(
                    jp.co.soramitsu.iroha2.generated.Container.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Container) = try {
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
        public val stringPredicate: StringPredicate
    ) : ValuePredicate() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Display>, ScaleWriter<Display> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Display = try {
                Display(
                    StringPredicate.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Display) = try {
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
        public val semiRange: SemiRange
    ) : ValuePredicate() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Numerical>, ScaleWriter<Numerical> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Numerical = try {
                Numerical(
                    SemiRange.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Numerical) = try {
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
        public val semiIntervalOfu128: SemiIntervalOfu128
    ) : ValuePredicate() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<TimeStamp>, ScaleWriter<TimeStamp> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): TimeStamp = try {
                TimeStamp(
                    SemiIntervalOfu128.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: TimeStamp) = try {
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
        public val ipv4Predicate: Ipv4Predicate
    ) : ValuePredicate() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Ipv4Addr>, ScaleWriter<Ipv4Addr> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): Ipv4Addr = try {
                Ipv4Addr(
                    Ipv4Predicate.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Ipv4Addr) = try {
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
        public val ipv6Predicate: Ipv6Predicate
    ) : ValuePredicate() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Ipv6Addr>, ScaleWriter<Ipv6Addr> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): Ipv6Addr = try {
                Ipv6Addr(
                    Ipv6Predicate.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Ipv6Addr) = try {
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
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Pass>, ScaleWriter<Pass> {
            public const val DISCRIMINANT: Int = 7

            public override fun read(reader: ScaleCodecReader): Pass = try {
                Pass()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Pass) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Pass, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".ValuePredicate.Pass".hashCode()
        }
    }

    public companion object : ScaleReader<ValuePredicate>, ScaleWriter<ValuePredicate> {
        public override fun read(reader: ScaleCodecReader): ValuePredicate = when (
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
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: ValuePredicate) {
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
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}