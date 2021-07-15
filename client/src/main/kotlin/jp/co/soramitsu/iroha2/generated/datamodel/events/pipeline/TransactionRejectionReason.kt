//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

/**
 * TransactionRejectionReason
 *
 * Generated from
 * 'jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.TransactionRejectionReason' enum
 */
public sealed class TransactionRejectionReason {
  /**
   * 'NotPermitted' variant
   */
  public class NotPermitted(
    private val notPermitted: NotPermittedFail
  ) : TransactionRejectionReason()

  /**
   * 'UnsatisfiedSignatureCondition' variant
   */
  public class UnsatisfiedSignatureCondition(
    private val unsatisfiedSignatureCondition: UnsatisfiedSignatureConditionFail
  ) : TransactionRejectionReason()

  /**
   * 'InstructionExecution' variant
   */
  public class InstructionExecution(
    private val instructionExecution: InstructionExecutionFail
  ) : TransactionRejectionReason()

  /**
   * 'SignatureVerification' variant
   */
  public class SignatureVerification(
    private val signatureVerification: SignatureVerificationFail
  ) : TransactionRejectionReason()

  /**
   * 'UnexpectedGenesisAccountSignature' variant
   */
  public class UnexpectedGenesisAccountSignature : TransactionRejectionReason()
}
