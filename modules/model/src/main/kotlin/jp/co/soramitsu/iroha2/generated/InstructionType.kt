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
 * InstructionType
 *
 * Generated from 'InstructionType' enum
 */
public sealed class InstructionType : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is Register -> Register.equals(this, other)
        is Unregister -> Unregister.equals(this, other)
        is Mint -> Mint.equals(this, other)
        is Burn -> Burn.equals(this, other)
        is Transfer -> Transfer.equals(this, other)
        is If -> If.equals(this, other)
        is Pair -> Pair.equals(this, other)
        is Sequence -> Sequence.equals(this, other)
        is SetKeyValue -> SetKeyValue.equals(this, other)
        is RemoveKeyValue -> RemoveKeyValue.equals(this, other)
        is Grant -> Grant.equals(this, other)
        is Revoke -> Revoke.equals(this, other)
        is ExecuteTrigger -> ExecuteTrigger.equals(this, other)
        is SetParameter -> SetParameter.equals(this, other)
        is NewParameter -> NewParameter.equals(this, other)
        is Upgrade -> Upgrade.equals(this, other)
        is Fail -> Fail.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is Register -> Register.hashCode()
        is Unregister -> Unregister.hashCode()
        is Mint -> Mint.hashCode()
        is Burn -> Burn.hashCode()
        is Transfer -> Transfer.hashCode()
        is If -> If.hashCode()
        is Pair -> Pair.hashCode()
        is Sequence -> Sequence.hashCode()
        is SetKeyValue -> SetKeyValue.hashCode()
        is RemoveKeyValue -> RemoveKeyValue.hashCode()
        is Grant -> Grant.hashCode()
        is Revoke -> Revoke.hashCode()
        is ExecuteTrigger -> ExecuteTrigger.hashCode()
        is SetParameter -> SetParameter.hashCode()
        is NewParameter -> NewParameter.hashCode()
        is Upgrade -> Upgrade.hashCode()
        is Fail -> Fail.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'Register' variant
     */
    public class Register : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Register>, ScaleWriter<Register> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Register = try {
                Register()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Register) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Register, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.Register".hashCode()
        }
    }

    /**
     * 'Unregister' variant
     */
    public class Unregister : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Unregister>, ScaleWriter<Unregister> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Unregister = try {
                Unregister()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Unregister) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Unregister, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.Unregister".hashCode()
        }
    }

    /**
     * 'Mint' variant
     */
    public class Mint : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Mint>, ScaleWriter<Mint> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Mint = try {
                Mint()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Mint) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Mint, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.Mint".hashCode()
        }
    }

    /**
     * 'Burn' variant
     */
    public class Burn : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Burn>, ScaleWriter<Burn> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Burn = try {
                Burn()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Burn) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Burn, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.Burn".hashCode()
        }
    }

    /**
     * 'Transfer' variant
     */
    public class Transfer : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Transfer>, ScaleWriter<Transfer> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): Transfer = try {
                Transfer()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Transfer) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Transfer, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.Transfer".hashCode()
        }
    }

    /**
     * 'If' variant
     */
    public class If : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<If>, ScaleWriter<If> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): If = try {
                If()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: If) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: If, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.If".hashCode()
        }
    }

    /**
     * 'Pair' variant
     */
    public class Pair : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Pair>, ScaleWriter<Pair> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): Pair = try {
                Pair()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Pair) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Pair, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.Pair".hashCode()
        }
    }

    /**
     * 'Sequence' variant
     */
    public class Sequence : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Sequence>, ScaleWriter<Sequence> {
            public const val DISCRIMINANT: Int = 7

            public override fun read(reader: ScaleCodecReader): Sequence = try {
                Sequence()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Sequence) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Sequence, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.Sequence".hashCode()
        }
    }

    /**
     * 'SetKeyValue' variant
     */
    public class SetKeyValue : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<SetKeyValue>, ScaleWriter<SetKeyValue> {
            public const val DISCRIMINANT: Int = 8

            public override fun read(reader: ScaleCodecReader): SetKeyValue = try {
                SetKeyValue()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: SetKeyValue) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: SetKeyValue, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.SetKeyValue".hashCode()
        }
    }

    /**
     * 'RemoveKeyValue' variant
     */
    public class RemoveKeyValue : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<RemoveKeyValue>, ScaleWriter<RemoveKeyValue> {
            public const val DISCRIMINANT: Int = 9

            public override fun read(reader: ScaleCodecReader): RemoveKeyValue = try {
                RemoveKeyValue()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: RemoveKeyValue) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: RemoveKeyValue, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.RemoveKeyValue".hashCode()
        }
    }

    /**
     * 'Grant' variant
     */
    public class Grant : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Grant>, ScaleWriter<Grant> {
            public const val DISCRIMINANT: Int = 10

            public override fun read(reader: ScaleCodecReader): Grant = try {
                Grant()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Grant) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Grant, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.Grant".hashCode()
        }
    }

    /**
     * 'Revoke' variant
     */
    public class Revoke : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Revoke>, ScaleWriter<Revoke> {
            public const val DISCRIMINANT: Int = 11

            public override fun read(reader: ScaleCodecReader): Revoke = try {
                Revoke()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Revoke) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Revoke, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.Revoke".hashCode()
        }
    }

    /**
     * 'ExecuteTrigger' variant
     */
    public class ExecuteTrigger : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ExecuteTrigger>, ScaleWriter<ExecuteTrigger> {
            public const val DISCRIMINANT: Int = 12

            public override fun read(reader: ScaleCodecReader): ExecuteTrigger = try {
                ExecuteTrigger()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ExecuteTrigger) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: ExecuteTrigger, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.ExecuteTrigger".hashCode()
        }
    }

    /**
     * 'SetParameter' variant
     */
    public class SetParameter : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<SetParameter>, ScaleWriter<SetParameter> {
            public const val DISCRIMINANT: Int = 13

            public override fun read(reader: ScaleCodecReader): SetParameter = try {
                SetParameter()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: SetParameter) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: SetParameter, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.SetParameter".hashCode()
        }
    }

    /**
     * 'NewParameter' variant
     */
    public class NewParameter : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<NewParameter>, ScaleWriter<NewParameter> {
            public const val DISCRIMINANT: Int = 14

            public override fun read(reader: ScaleCodecReader): NewParameter = try {
                NewParameter()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: NewParameter) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: NewParameter, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.NewParameter".hashCode()
        }
    }

    /**
     * 'Upgrade' variant
     */
    public class Upgrade : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Upgrade>, ScaleWriter<Upgrade> {
            public const val DISCRIMINANT: Int = 15

            public override fun read(reader: ScaleCodecReader): Upgrade = try {
                Upgrade()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Upgrade) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Upgrade, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.Upgrade".hashCode()
        }
    }

    /**
     * 'Fail' variant
     */
    public class Fail : InstructionType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Fail>, ScaleWriter<Fail> {
            public const val DISCRIMINANT: Int = 16

            public override fun read(reader: ScaleCodecReader): Fail = try {
                Fail()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Fail) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Fail, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = ".InstructionType.Fail".hashCode()
        }
    }

    public companion object : ScaleReader<InstructionType>, ScaleWriter<InstructionType> {
        public override fun read(reader: ScaleCodecReader): InstructionType = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Register.read(reader)
            1 -> Unregister.read(reader)
            2 -> Mint.read(reader)
            3 -> Burn.read(reader)
            4 -> Transfer.read(reader)
            5 -> If.read(reader)
            6 -> Pair.read(reader)
            7 -> Sequence.read(reader)
            8 -> SetKeyValue.read(reader)
            9 -> RemoveKeyValue.read(reader)
            10 -> Grant.read(reader)
            11 -> Revoke.read(reader)
            12 -> ExecuteTrigger.read(reader)
            13 -> SetParameter.read(reader)
            14 -> NewParameter.read(reader)
            15 -> Upgrade.read(reader)
            16 -> Fail.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: InstructionType) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Register.write(writer, instance as Register)
                1 -> Unregister.write(writer, instance as Unregister)
                2 -> Mint.write(writer, instance as Mint)
                3 -> Burn.write(writer, instance as Burn)
                4 -> Transfer.write(writer, instance as Transfer)
                5 -> If.write(writer, instance as If)
                6 -> Pair.write(writer, instance as Pair)
                7 -> Sequence.write(writer, instance as Sequence)
                8 -> SetKeyValue.write(writer, instance as SetKeyValue)
                9 -> RemoveKeyValue.write(writer, instance as RemoveKeyValue)
                10 -> Grant.write(writer, instance as Grant)
                11 -> Revoke.write(writer, instance as Revoke)
                12 -> ExecuteTrigger.write(writer, instance as ExecuteTrigger)
                13 -> SetParameter.write(writer, instance as SetParameter)
                14 -> NewParameter.write(writer, instance as NewParameter)
                15 -> Upgrade.write(writer, instance as Upgrade)
                16 -> Fail.write(writer, instance as Fail)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
