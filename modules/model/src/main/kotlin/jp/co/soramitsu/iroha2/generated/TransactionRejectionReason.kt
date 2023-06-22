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
 * TransactionRejectionReason
 *
 * Generated from 'TransactionRejectionReason' enum
 */
public sealed class TransactionRejectionReason : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is UnexpectedGenesisAccountSignature -> UnexpectedGenesisAccountSignature.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is UnexpectedGenesisAccountSignature -> UnexpectedGenesisAccountSignature.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'AccountDoesNotExist' variant
     */
    public data class AccountDoesNotExist(
        public val findError: FindError
    ) : TransactionRejectionReason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AccountDoesNotExist>, ScaleWriter<AccountDoesNotExist> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): AccountDoesNotExist = try {
                AccountDoesNotExist(
                    FindError.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AccountDoesNotExist) = try {
                FindError.write(writer, instance.findError)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'LimitCheck' variant
     */
    public data class LimitCheck(
        public val transactionLimitError: TransactionLimitError
    ) : TransactionRejectionReason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<LimitCheck>, ScaleWriter<LimitCheck> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): LimitCheck = try {
                LimitCheck(
                    TransactionLimitError.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: LimitCheck) = try {
                TransactionLimitError.write(writer, instance.transactionLimitError)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Validation' variant
     */
    public data class Validation(
        public val validationFail: ValidationFail
    ) : TransactionRejectionReason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Validation>, ScaleWriter<Validation> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Validation = try {
                Validation(
                    ValidationFail.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Validation) = try {
                ValidationFail.write(writer, instance.validationFail)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'UnsatisfiedSignatureCondition' variant
     */
    public data class UnsatisfiedSignatureCondition(
        public val unsatisfiedSignatureConditionFail: UnsatisfiedSignatureConditionFail
    ) : TransactionRejectionReason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<UnsatisfiedSignatureCondition>,
            ScaleWriter<UnsatisfiedSignatureCondition> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): UnsatisfiedSignatureCondition = try {
                UnsatisfiedSignatureCondition(
                    UnsatisfiedSignatureConditionFail.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: UnsatisfiedSignatureCondition) =
                try {
                    UnsatisfiedSignatureConditionFail.write(writer, instance.unsatisfiedSignatureConditionFail)
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }
        }
    }

    /**
     * 'InstructionExecution' variant
     */
    public data class InstructionExecution(
        public val instructionExecutionFail: InstructionExecutionFail
    ) : TransactionRejectionReason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<InstructionExecution>, ScaleWriter<InstructionExecution> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): InstructionExecution = try {
                InstructionExecution(
                    InstructionExecutionFail.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: InstructionExecution) = try {
                InstructionExecutionFail.write(writer, instance.instructionExecutionFail)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'WasmExecution' variant
     */
    public data class WasmExecution(
        public val wasmExecutionFail: WasmExecutionFail
    ) : TransactionRejectionReason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<WasmExecution>, ScaleWriter<WasmExecution> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): WasmExecution = try {
                WasmExecution(
                    WasmExecutionFail.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: WasmExecution) = try {
                WasmExecutionFail.write(writer, instance.wasmExecutionFail)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'UnexpectedGenesisAccountSignature' variant
     */
    public class UnexpectedGenesisAccountSignature : TransactionRejectionReason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<UnexpectedGenesisAccountSignature>,
            ScaleWriter<UnexpectedGenesisAccountSignature> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): UnexpectedGenesisAccountSignature = try {
                UnexpectedGenesisAccountSignature()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(
                writer: ScaleCodecWriter,
                instance: UnexpectedGenesisAccountSignature
            ) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: UnexpectedGenesisAccountSignature, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int =
                ".TransactionRejectionReason.UnexpectedGenesisAccountSignature".hashCode()
        }
    }

    /**
     * 'Expired' variant
     */
    public data class Expired(
        public val transactionExpired: TransactionExpired
    ) : TransactionRejectionReason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Expired>, ScaleWriter<Expired> {
            public const val DISCRIMINANT: Int = 7

            public override fun read(reader: ScaleCodecReader): Expired = try {
                Expired(
                    TransactionExpired.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Expired) = try {
                TransactionExpired.write(writer, instance.transactionExpired)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object :
        ScaleReader<TransactionRejectionReason>,
        ScaleWriter<TransactionRejectionReason> {
        public override fun read(reader: ScaleCodecReader): TransactionRejectionReason = when (
            val
            discriminant = reader.readUByte()
        ) {
            0 -> AccountDoesNotExist.read(reader)
            1 -> LimitCheck.read(reader)
            2 -> Validation.read(reader)
            3 -> UnsatisfiedSignatureCondition.read(reader)
            4 -> InstructionExecution.read(reader)
            5 -> WasmExecution.read(reader)
            6 -> UnexpectedGenesisAccountSignature.read(reader)
            7 -> Expired.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: TransactionRejectionReason) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> AccountDoesNotExist.write(writer, instance as AccountDoesNotExist)
                1 -> LimitCheck.write(writer, instance as LimitCheck)
                2 -> Validation.write(writer, instance as Validation)
                3 -> UnsatisfiedSignatureCondition.write(writer, instance as UnsatisfiedSignatureCondition)
                4 -> InstructionExecution.write(writer, instance as InstructionExecution)
                5 -> WasmExecution.write(writer, instance as WasmExecution)
                6 -> UnexpectedGenesisAccountSignature.write(
                    writer,
                    instance as
                        UnexpectedGenesisAccountSignature
                )
                7 -> Expired.write(writer, instance as Expired)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
