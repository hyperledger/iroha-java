//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

/**
 * Status
 *
 * Generated from 'jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.Status' enum
 */
public sealed class Status {
  /**
   * 'Validating' variant
   */
  public class Validating : Status()

  /**
   * 'Rejected' variant
   */
  public class Rejected(
    private val rejected: RejectionReason
  ) : Status()

  /**
   * 'Committed' variant
   */
  public class Committed : Status()
}
