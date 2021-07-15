//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events

/**
 * EventFilter
 *
 * Generated from 'jp.co.soramitsu.iroha2.generated.datamodel.events.EventFilter' enum
 */
public sealed class EventFilter {
  /**
   * 'Pipeline' variant
   */
  public class Pipeline(
    private val pipeline: jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.EventFilter
  ) : EventFilter()

  /**
   * 'Data' variant
   */
  public class Data(
    private val `data`: jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.EventFilter
  ) : EventFilter()
}
