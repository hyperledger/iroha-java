//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events

/**
 * Event
 *
 * Generated from 'jp.co.soramitsu.iroha2.generated.datamodel.events.Event' enum
 */
public sealed class Event {
  /**
   * 'Pipeline' variant
   */
  public class Pipeline(
    private val pipeline: jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.Event
  ) : Event()

  /**
   * 'Data' variant
   */
  public class Data(
    private val `data`: jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.Event
  ) : Event()
}
