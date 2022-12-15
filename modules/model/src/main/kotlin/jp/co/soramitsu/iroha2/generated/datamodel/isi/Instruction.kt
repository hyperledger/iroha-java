//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * Instruction
 *
 * Generated from 'iroha_data_model::isi::Instruction' enum
 */
public sealed class Instruction : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Register' variant
     */
    public data class Register(
        public val registerBox: RegisterBox
    ) : Instruction() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Register>, ScaleWriter<Register> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Register = try {
                Register(
                    RegisterBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Register) = try {
                RegisterBox.write(writer, instance.registerBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Unregister' variant
     */
    public data class Unregister(
        public val unregisterBox: UnregisterBox
    ) : Instruction() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Unregister>, ScaleWriter<Unregister> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Unregister = try {
                Unregister(
                    UnregisterBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Unregister) = try {
                UnregisterBox.write(writer, instance.unregisterBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Mint' variant
     */
    public data class Mint(
        public val mintBox: MintBox
    ) : Instruction() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Mint>, ScaleWriter<Mint> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Mint = try {
                Mint(
                    MintBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Mint) = try {
                MintBox.write(writer, instance.mintBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Burn' variant
     */
    public data class Burn(
        public val burnBox: BurnBox
    ) : Instruction() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Burn>, ScaleWriter<Burn> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Burn = try {
                Burn(
                    BurnBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Burn) = try {
                BurnBox.write(writer, instance.burnBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Transfer' variant
     */
    public data class Transfer(
        public val transferBox: TransferBox
    ) : Instruction() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Transfer>, ScaleWriter<Transfer> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): Transfer = try {
                Transfer(
                    TransferBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Transfer) = try {
                TransferBox.write(writer, instance.transferBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'If' variant
     */
    public data class If(
        public val `if`: jp.co.soramitsu.iroha2.generated.datamodel.isi.If
    ) : Instruction() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<If>, ScaleWriter<If> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): If = try {
                If(
                    jp.co.soramitsu.iroha2.generated.datamodel.isi.If.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: If) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.isi.If.write(writer, instance.`if`)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Pair' variant
     */
    public data class Pair(
        public val pair: jp.co.soramitsu.iroha2.generated.datamodel.isi.Pair
    ) : Instruction() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Pair>, ScaleWriter<Pair> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): Pair = try {
                Pair(
                    jp.co.soramitsu.iroha2.generated.datamodel.isi.Pair.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Pair) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.isi.Pair.write(writer, instance.pair)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Sequence' variant
     */
    public data class Sequence(
        public val sequenceBox: SequenceBox
    ) : Instruction() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Sequence>, ScaleWriter<Sequence> {
            public const val DISCRIMINANT: Int = 7

            public override fun read(reader: ScaleCodecReader): Sequence = try {
                Sequence(
                    SequenceBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Sequence) = try {
                SequenceBox.write(writer, instance.sequenceBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Fail' variant
     */
    public data class Fail(
        public val failBox: FailBox
    ) : Instruction() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Fail>, ScaleWriter<Fail> {
            public const val DISCRIMINANT: Int = 8

            public override fun read(reader: ScaleCodecReader): Fail = try {
                Fail(
                    FailBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Fail) = try {
                FailBox.write(writer, instance.failBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'SetKeyValue' variant
     */
    public data class SetKeyValue(
        public val setKeyValueBox: SetKeyValueBox
    ) : Instruction() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<SetKeyValue>, ScaleWriter<SetKeyValue> {
            public const val DISCRIMINANT: Int = 9

            public override fun read(reader: ScaleCodecReader): SetKeyValue = try {
                SetKeyValue(
                    SetKeyValueBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: SetKeyValue) = try {
                SetKeyValueBox.write(writer, instance.setKeyValueBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'RemoveKeyValue' variant
     */
    public data class RemoveKeyValue(
        public val removeKeyValueBox: RemoveKeyValueBox
    ) : Instruction() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<RemoveKeyValue>, ScaleWriter<RemoveKeyValue> {
            public const val DISCRIMINANT: Int = 10

            public override fun read(reader: ScaleCodecReader): RemoveKeyValue = try {
                RemoveKeyValue(
                    RemoveKeyValueBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: RemoveKeyValue) = try {
                RemoveKeyValueBox.write(writer, instance.removeKeyValueBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Grant' variant
     */
    public data class Grant(
        public val grantBox: GrantBox
    ) : Instruction() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Grant>, ScaleWriter<Grant> {
            public const val DISCRIMINANT: Int = 11

            public override fun read(reader: ScaleCodecReader): Grant = try {
                Grant(
                    GrantBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Grant) = try {
                GrantBox.write(writer, instance.grantBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Revoke' variant
     */
    public data class Revoke(
        public val revokeBox: RevokeBox
    ) : Instruction() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Revoke>, ScaleWriter<Revoke> {
            public const val DISCRIMINANT: Int = 12

            public override fun read(reader: ScaleCodecReader): Revoke = try {
                Revoke(
                    RevokeBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Revoke) = try {
                RevokeBox.write(writer, instance.revokeBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ExecuteTrigger' variant
     */
    public data class ExecuteTrigger(
        public val executeTriggerBox: ExecuteTriggerBox
    ) : Instruction() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ExecuteTrigger>, ScaleWriter<ExecuteTrigger> {
            public const val DISCRIMINANT: Int = 13

            public override fun read(reader: ScaleCodecReader): ExecuteTrigger = try {
                ExecuteTrigger(
                    ExecuteTriggerBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ExecuteTrigger) = try {
                ExecuteTriggerBox.write(writer, instance.executeTriggerBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Instruction>, ScaleWriter<Instruction> {
        public override fun read(reader: ScaleCodecReader): Instruction = when (
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
            8 -> Fail.read(reader)
            9 -> SetKeyValue.read(reader)
            10 -> RemoveKeyValue.read(reader)
            11 -> Grant.read(reader)
            12 -> Revoke.read(reader)
            13 -> ExecuteTrigger.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Instruction) {
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
                8 -> Fail.write(writer, instance as Fail)
                9 -> SetKeyValue.write(writer, instance as SetKeyValue)
                10 -> RemoveKeyValue.write(writer, instance as RemoveKeyValue)
                11 -> Grant.write(writer, instance as Grant)
                12 -> Revoke.write(writer, instance as Revoke)
                13 -> ExecuteTrigger.write(writer, instance as ExecuteTrigger)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
