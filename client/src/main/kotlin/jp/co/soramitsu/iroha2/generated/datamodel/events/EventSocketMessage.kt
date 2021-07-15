//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events

/**
 * EventSocketMessage
 *
 * Generated from 'jp.co.soramitsu.iroha2.generated.datamodel.events.EventSocketMessage' enum
 */
public sealed class EventSocketMessage {
  /**
   * 'SubscriptionRequest' variant
   */
  public class SubscriptionRequest(
    private val subscriptionRequest:
        jp.co.soramitsu.iroha2.generated.datamodel.events.SubscriptionRequest
  ) : EventSocketMessage()

  /**
   * 'SubscriptionAccepted' variant
   */
  public class SubscriptionAccepted : EventSocketMessage()

  /**
   * 'Event' variant
   */
  public class Event(
    private val event: jp.co.soramitsu.iroha2.generated.datamodel.events.Event
  ) : EventSocketMessage()

  /**
   * 'EventReceived' variant
   */
  public class EventReceived : EventSocketMessage()
}
