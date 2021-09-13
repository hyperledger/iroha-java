//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * TransactionRejectionReason
 *
 * Generated from 'iroha_data_model::events::pipeline::TransactionRejectionReason' enum
 */
public sealed class TransactionRejectionReason {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'NotPermitted' variant
     */
    public data class NotPermitted(
        public val notPermittedFail: NotPermittedFail
    ) : TransactionRejectionReason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<NotPermitted>, ScaleWriter<NotPermitted> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): NotPermitted = try {
                NotPermitted(
                    NotPermittedFail.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: NotPermitted) = try {
                NotPermittedFail.write(writer, instance.notPermittedFail)
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
            public const val DISCRIMINANT: Int = 1

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
            public const val DISCRIMINANT: Int = 2

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
     * 'SignatureVerification' variant
     */
    public data class SignatureVerification(
        public val signatureVerificationFail: SignatureVerificationFail
    ) : TransactionRejectionReason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<SignatureVerification>, ScaleWriter<SignatureVerification> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): SignatureVerification = try {
                SignatureVerification(
                    SignatureVerificationFail.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: SignatureVerification) = try {
                SignatureVerificationFail.write(writer, instance.signatureVerificationFail)
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
            public const val DISCRIMINANT: Int = 4

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
        }
    }

    public companion object :
        ScaleReader<TransactionRejectionReason>,
        ScaleWriter<TransactionRejectionReason> {
        public override fun read(reader: ScaleCodecReader): TransactionRejectionReason = when (
            val
            discriminant = reader.readUByte()
        ) {
            0 -> NotPermitted.read(reader)
            1 -> UnsatisfiedSignatureCondition.read(reader)
            2 -> InstructionExecution.read(reader)
            3 -> SignatureVerification.read(reader)
            4 -> UnexpectedGenesisAccountSignature.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: TransactionRejectionReason) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> NotPermitted.write(writer, instance as NotPermitted)
                1 -> UnsatisfiedSignatureCondition.write(writer, instance as UnsatisfiedSignatureCondition)
                2 -> InstructionExecution.write(writer, instance as InstructionExecution)
                3 -> SignatureVerification.write(writer, instance as SignatureVerification)
                4 -> UnexpectedGenesisAccountSignature.write(
                    writer,
                    instance as
                        UnexpectedGenesisAccountSignature
                )
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
