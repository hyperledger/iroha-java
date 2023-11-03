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
 * InstructionExpr
 *
 * Generated from 'InstructionExpr' enum
 */
public sealed class InstructionExpr : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Register' variant
     */
    public data class Register(
        public val registerExpr: RegisterExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.Register>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.Register> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.Register = try {
                Register(
                    RegisterExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.Register,
            ): Unit = try {
                RegisterExpr.write(writer, instance.registerExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Unregister' variant
     */
    public data class Unregister(
        public val unregisterExpr: UnregisterExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.Unregister>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.Unregister> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.Unregister = try {
                Unregister(
                    UnregisterExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.Unregister,
            ): Unit = try {
                UnregisterExpr.write(writer, instance.unregisterExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Mint' variant
     */
    public data class Mint(
        public val mintExpr: MintExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.Mint>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.Mint> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.Mint = try {
                Mint(
                    MintExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.Mint,
            ): Unit = try {
                MintExpr.write(writer, instance.mintExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Burn' variant
     */
    public data class Burn(
        public val burnExpr: BurnExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.Burn>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.Burn> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.Burn = try {
                Burn(
                    BurnExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.Burn,
            ): Unit = try {
                BurnExpr.write(writer, instance.burnExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Transfer' variant
     */
    public data class Transfer(
        public val transferExpr: TransferExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.Transfer>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.Transfer> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.Transfer = try {
                Transfer(
                    TransferExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.Transfer,
            ): Unit = try {
                TransferExpr.write(writer, instance.transferExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'If' variant
     */
    public data class If(
        public val conditionalExpr: ConditionalExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.If>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.If> {
            public const val DISCRIMINANT: Int = 5

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.If = try {
                If(
                    ConditionalExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.If,
            ): Unit = try {
                ConditionalExpr.write(writer, instance.conditionalExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Pair' variant
     */
    public data class Pair(
        public val pairExpr: PairExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.Pair>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.Pair> {
            public const val DISCRIMINANT: Int = 6

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.Pair = try {
                Pair(
                    PairExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.Pair,
            ): Unit = try {
                PairExpr.write(writer, instance.pairExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Sequence' variant
     */
    public data class Sequence(
        public val sequenceExpr: SequenceExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.Sequence>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.Sequence> {
            public const val DISCRIMINANT: Int = 7

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.Sequence = try {
                Sequence(
                    SequenceExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.Sequence,
            ): Unit = try {
                SequenceExpr.write(writer, instance.sequenceExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'SetKeyValue' variant
     */
    public data class SetKeyValue(
        public val setKeyValueExpr: SetKeyValueExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.SetKeyValue>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.SetKeyValue> {
            public const val DISCRIMINANT: Int = 8

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.SetKeyValue = try {
                SetKeyValue(
                    SetKeyValueExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.SetKeyValue,
            ): Unit = try {
                SetKeyValueExpr.write(writer, instance.setKeyValueExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'RemoveKeyValue' variant
     */
    public data class RemoveKeyValue(
        public val removeKeyValueExpr: RemoveKeyValueExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.RemoveKeyValue>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.RemoveKeyValue> {
            public const val DISCRIMINANT: Int = 9

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.RemoveKeyValue = try {
                RemoveKeyValue(
                    RemoveKeyValueExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.RemoveKeyValue,
            ): Unit = try {
                RemoveKeyValueExpr.write(writer, instance.removeKeyValueExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Grant' variant
     */
    public data class Grant(
        public val grantExpr: GrantExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.Grant>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.Grant> {
            public const val DISCRIMINANT: Int = 10

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.Grant = try {
                Grant(
                    GrantExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.Grant,
            ): Unit = try {
                GrantExpr.write(writer, instance.grantExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Revoke' variant
     */
    public data class Revoke(
        public val revokeExpr: RevokeExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.Revoke>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.Revoke> {
            public const val DISCRIMINANT: Int = 11

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.Revoke = try {
                Revoke(
                    RevokeExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.Revoke,
            ): Unit = try {
                RevokeExpr.write(writer, instance.revokeExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ExecuteTrigger' variant
     */
    public data class ExecuteTrigger(
        public val executeTriggerExpr: ExecuteTriggerExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.ExecuteTrigger>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.ExecuteTrigger> {
            public const val DISCRIMINANT: Int = 12

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.ExecuteTrigger = try {
                ExecuteTrigger(
                    ExecuteTriggerExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.ExecuteTrigger,
            ): Unit = try {
                ExecuteTriggerExpr.write(writer, instance.executeTriggerExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'SetParameter' variant
     */
    public data class SetParameter(
        public val setParameterExpr: SetParameterExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.SetParameter>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.SetParameter> {
            public const val DISCRIMINANT: Int = 13

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.SetParameter = try {
                SetParameter(
                    SetParameterExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.SetParameter,
            ): Unit = try {
                SetParameterExpr.write(writer, instance.setParameterExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'NewParameter' variant
     */
    public data class NewParameter(
        public val newParameterExpr: NewParameterExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.NewParameter>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.NewParameter> {
            public const val DISCRIMINANT: Int = 14

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.NewParameter = try {
                NewParameter(
                    NewParameterExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.NewParameter,
            ): Unit = try {
                NewParameterExpr.write(writer, instance.newParameterExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Upgrade' variant
     */
    public data class Upgrade(
        public val upgradeExpr: UpgradeExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.Upgrade>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.Upgrade> {
            public const val DISCRIMINANT: Int = 15

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.Upgrade = try {
                Upgrade(
                    UpgradeExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.Upgrade,
            ): Unit = try {
                UpgradeExpr.write(writer, instance.upgradeExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Log' variant
     */
    public data class Log(
        public val logExpr: LogExpr,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.Log>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.Log> {
            public const val DISCRIMINANT: Int = 16

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.Log = try {
                Log(
                    LogExpr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.Log,
            ): Unit = try {
                LogExpr.write(writer, instance.logExpr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Fail' variant
     */
    public data class Fail(
        public val fail: jp.co.soramitsu.iroha2.generated.Fail,
    ) : InstructionExpr() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.InstructionExpr.Fail>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.InstructionExpr.Fail> {
            public const val DISCRIMINANT: Int = 17

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.InstructionExpr.Fail = try {
                Fail(
                    jp.co.soramitsu.iroha2.generated.Fail.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.InstructionExpr.Fail,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Fail.write(writer, instance.fail)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<InstructionExpr>, ScaleWriter<InstructionExpr> {
        override fun read(reader: ScaleCodecReader): InstructionExpr = when (
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
            16 -> Log.read(reader)
            17 -> Fail.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: InstructionExpr) {
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
                16 -> Log.write(writer, instance as Log)
                17 -> Fail.write(writer, instance as Fail)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
