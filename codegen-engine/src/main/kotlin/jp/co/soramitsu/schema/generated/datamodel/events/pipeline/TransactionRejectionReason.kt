// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.events.pipeline

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleReader
import kotlin.Int

/**
 * TransactionRejectionReason
 *
 * Generated from 'iroha_data_model::events::pipeline::TransactionRejectionReason' enum
 */
public abstract class TransactionRejectionReason {
  /**
   * @return Discriminator of variant in enum
   */
  public abstract fun discriminant(): Int

  /**
   * 'NotPermitted' variant
   */
  public class NotPermitted(
    private val notPermitted: NotPermittedFail
  ) : TransactionRejectionReason() {
    public override fun discriminant(): Int = 0

    public companion object READER : ScaleReader<NotPermitted> {
      public override fun read(reader: ScaleCodecReader): NotPermitted {
      }
    }
  }

  /**
   * 'UnsatisfiedSignatureCondition' variant
   */
  public class UnsatisfiedSignatureCondition(
    private val unsatisfiedSignatureCondition: UnsatisfiedSignatureConditionFail
  ) : TransactionRejectionReason() {
    public override fun discriminant(): Int = 1

    public companion object READER : ScaleReader<UnsatisfiedSignatureCondition> {
      public override fun read(reader: ScaleCodecReader): UnsatisfiedSignatureCondition {
      }
    }
  }

  /**
   * 'InstructionExecution' variant
   */
  public class InstructionExecution(
    private val instructionExecution: InstructionExecutionFail
  ) : TransactionRejectionReason() {
    public override fun discriminant(): Int = 2

    public companion object READER : ScaleReader<InstructionExecution> {
      public override fun read(reader: ScaleCodecReader): InstructionExecution {
      }
    }
  }

  /**
   * 'SignatureVerification' variant
   */
  public class SignatureVerification(
    private val signatureVerification: SignatureVerificationFail
  ) : TransactionRejectionReason() {
    public override fun discriminant(): Int = 3

    public companion object READER : ScaleReader<SignatureVerification> {
      public override fun read(reader: ScaleCodecReader): SignatureVerification {
      }
    }
  }

  /**
   * 'UnexpectedGenesisAccountSignature' variant
   */
  public class UnexpectedGenesisAccountSignature : TransactionRejectionReason() {
    public override fun discriminant(): Int = 4
  }
}
