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
 * InstructionBox
 *
 * Generated from 'InstructionBox' enum
 */
public sealed class InstructionBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Register' variant
     */
    public data class Register(
        public val registerBox: RegisterBox
    ) : InstructionBox() {
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
    ) : InstructionBox() {
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
    ) : InstructionBox() {
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
    ) : InstructionBox() {
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
    ) : InstructionBox() {
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
        public val conditional: Conditional
    ) : InstructionBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<If>, ScaleWriter<If> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): If = try {
                If(
                    Conditional.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: If) = try {
                Conditional.write(writer, instance.conditional)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Pair' variant
     */
    public data class Pair(
        public val pair: jp.co.soramitsu.iroha2.generated.Pair
    ) : InstructionBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Pair>, ScaleWriter<Pair> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): Pair = try {
                Pair(
                    jp.co.soramitsu.iroha2.generated.Pair.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Pair) = try {
                jp.co.soramitsu.iroha2.generated.Pair.write(writer, instance.pair)
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
    ) : InstructionBox() {
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
     * 'SetKeyValue' variant
     */
    public data class SetKeyValue(
        public val setKeyValueBox: SetKeyValueBox
    ) : InstructionBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<SetKeyValue>, ScaleWriter<SetKeyValue> {
            public const val DISCRIMINANT: Int = 8

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
    ) : InstructionBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<RemoveKeyValue>, ScaleWriter<RemoveKeyValue> {
            public const val DISCRIMINANT: Int = 9

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
    ) : InstructionBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Grant>, ScaleWriter<Grant> {
            public const val DISCRIMINANT: Int = 10

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
    ) : InstructionBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Revoke>, ScaleWriter<Revoke> {
            public const val DISCRIMINANT: Int = 11

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
    ) : InstructionBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ExecuteTrigger>, ScaleWriter<ExecuteTrigger> {
            public const val DISCRIMINANT: Int = 12

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

    /**
     * 'SetParameter' variant
     */
    public data class SetParameter(
        public val setParameterBox: SetParameterBox
    ) : InstructionBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<SetParameter>, ScaleWriter<SetParameter> {
            public const val DISCRIMINANT: Int = 13

            public override fun read(reader: ScaleCodecReader): SetParameter = try {
                SetParameter(
                    SetParameterBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: SetParameter) = try {
                SetParameterBox.write(writer, instance.setParameterBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'NewParameter' variant
     */
    public data class NewParameter(
        public val newParameterBox: NewParameterBox
    ) : InstructionBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<NewParameter>, ScaleWriter<NewParameter> {
            public const val DISCRIMINANT: Int = 14

            public override fun read(reader: ScaleCodecReader): NewParameter = try {
                NewParameter(
                    NewParameterBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: NewParameter) = try {
                NewParameterBox.write(writer, instance.newParameterBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Upgrade' variant
     */
    public data class Upgrade(
        public val upgradeBox: UpgradeBox
    ) : InstructionBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Upgrade>, ScaleWriter<Upgrade> {
            public const val DISCRIMINANT: Int = 15

            public override fun read(reader: ScaleCodecReader): Upgrade = try {
                Upgrade(
                    UpgradeBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Upgrade) = try {
                UpgradeBox.write(writer, instance.upgradeBox)
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
    ) : InstructionBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Fail>, ScaleWriter<Fail> {
            public const val DISCRIMINANT: Int = 16

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

    public companion object : ScaleReader<InstructionBox>, ScaleWriter<InstructionBox> {
        public override fun read(reader: ScaleCodecReader): InstructionBox = when (
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

        public override fun write(writer: ScaleCodecWriter, instance: InstructionBox) {
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
