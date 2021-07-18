//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Int
import kotlin.Unit

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
  public class NotPermitted(
    private val notPermittedFail: NotPermittedFail
  ) : TransactionRejectionReason() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<NotPermitted>, ScaleWriter<NotPermitted> {
      public const val DISCRIMINANT: Int = 0

      public override fun read(reader: ScaleCodecReader): NotPermitted =
          NotPermitted(NotPermittedFail.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: NotPermitted): Unit {
        NotPermittedFail.write(writer, instance.notPermittedFail)
      }
    }
  }

  /**
   * 'UnsatisfiedSignatureCondition' variant
   */
  public class UnsatisfiedSignatureCondition(
    private val unsatisfiedSignatureConditionFail: UnsatisfiedSignatureConditionFail
  ) : TransactionRejectionReason() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<UnsatisfiedSignatureCondition>,
        ScaleWriter<UnsatisfiedSignatureCondition> {
      public const val DISCRIMINANT: Int = 1

      public override fun read(reader: ScaleCodecReader): UnsatisfiedSignatureCondition =
          UnsatisfiedSignatureCondition(UnsatisfiedSignatureConditionFail.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: UnsatisfiedSignatureCondition):
          Unit {
        UnsatisfiedSignatureConditionFail.write(writer, instance.unsatisfiedSignatureConditionFail)
      }
    }
  }

  /**
   * 'InstructionExecution' variant
   */
  public class InstructionExecution(
    private val instructionExecutionFail: InstructionExecutionFail
  ) : TransactionRejectionReason() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<InstructionExecution>, ScaleWriter<InstructionExecution> {
      public const val DISCRIMINANT: Int = 2

      public override fun read(reader: ScaleCodecReader): InstructionExecution =
          InstructionExecution(InstructionExecutionFail.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: InstructionExecution): Unit {
        InstructionExecutionFail.write(writer, instance.instructionExecutionFail)
      }
    }
  }

  /**
   * 'SignatureVerification' variant
   */
  public class SignatureVerification(
    private val signatureVerificationFail: SignatureVerificationFail
  ) : TransactionRejectionReason() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<SignatureVerification>, ScaleWriter<SignatureVerification>
        {
      public const val DISCRIMINANT: Int = 3

      public override fun read(reader: ScaleCodecReader): SignatureVerification =
          SignatureVerification(SignatureVerificationFail.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: SignatureVerification): Unit {
        SignatureVerificationFail.write(writer, instance.signatureVerificationFail)
      }
    }
  }

  /**
   * 'UnexpectedGenesisAccountSignature' variant
   */
  public class UnexpectedGenesisAccountSignature : TransactionRejectionReason() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<UnexpectedGenesisAccountSignature>,
        ScaleWriter<UnexpectedGenesisAccountSignature> {
      public const val DISCRIMINANT: Int = 4

      public override fun read(reader: ScaleCodecReader): UnexpectedGenesisAccountSignature =
          UnexpectedGenesisAccountSignature()

      public override fun write(writer: ScaleCodecWriter,
          instance: UnexpectedGenesisAccountSignature): Unit {
      }
    }
  }

  public companion object : ScaleReader<TransactionRejectionReason>,
      ScaleWriter<TransactionRejectionReason> {
    public override fun read(reader: ScaleCodecReader): TransactionRejectionReason = when(val
        discriminant = reader.readUByte()) {
    	0 -> NotPermitted.read(reader)
    	1 -> UnsatisfiedSignatureCondition.read(reader)
    	2 -> InstructionExecution.read(reader)
    	3 -> SignatureVerification.read(reader)
    	4 -> UnexpectedGenesisAccountSignature.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}

    public override fun write(writer: ScaleCodecWriter, instance: TransactionRejectionReason):
        Unit {
      writer.directWrite(instance.discriminant())
      when(val discriminant = instance.discriminant()) {
      	0 -> NotPermitted.write(writer, instance as NotPermitted)
      	1 -> UnsatisfiedSignatureCondition.write(writer, instance as UnsatisfiedSignatureCondition)
      	2 -> InstructionExecution.write(writer, instance as InstructionExecution)
      	3 -> SignatureVerification.write(writer, instance as SignatureVerification)
      	4 -> UnexpectedGenesisAccountSignature.write(writer, instance as
          UnexpectedGenesisAccountSignature)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}
    }
  }
}
