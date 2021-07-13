//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.Event.EntityType
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.Event.Hash
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.Event.Status

/**
 * Event
 *
 * Generated from 'iroha_data_model::events::pipeline::Event' regular structure
 */
public class Event(
  public val entityType: EntityType,
  public val status: Status,
  public val hash: Hash
)
