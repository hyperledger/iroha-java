package jp.co.soramitsu.iroha2.transaction

import jp.co.soramitsu.iroha2.generated.Duration
import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.events.EventsFilterBox
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.events.account.AccountEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.events.account.AccountFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.events.asset.AssetDefinitionEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.events.asset.AssetDefinitionFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.events.asset.AssetEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.events.asset.AssetFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.events.domain.DomainEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.events.domain.DomainFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.events.peer.PeerEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.events.peer.PeerFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.events.role.RoleEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.events.role.RoleFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.events.trigger.TriggerEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.events.trigger.TriggerFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.EntityFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptAccountEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptAccountFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptAssetDefinitionEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptAssetDefinitionFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptAssetEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptAssetFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptDomainEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptDomainFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptEntityFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptOriginFilterAccountEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptOriginFilterAssetDefinitionEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptOriginFilterAssetEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptOriginFilterDomainEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptOriginFilterPeerEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptOriginFilterRoleEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptOriginFilterTriggerEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptPeerEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptPeerFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptRoleEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptRoleFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptTriggerEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptTriggerFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.OriginFilterAccountEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.OriginFilterAssetDefinitionEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.OriginFilterAssetEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.OriginFilterDomainEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.OriginFilterPeerEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.OriginFilterRoleEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.OriginFilterTriggerEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.executetrigger.ExecuteTriggerEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.EntityKind
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.PipelineEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.StatusKind
import jp.co.soramitsu.iroha2.generated.datamodel.events.time.ExecutionTime
import jp.co.soramitsu.iroha2.generated.datamodel.events.time.Schedule
import jp.co.soramitsu.iroha2.generated.datamodel.events.time.TimeEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.PredicateBox
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.value.Predicate
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.string.Predicate as QueryPredicate

/**
 * Filters are used to filter out events
 */
@Suppress("unused")
object Filters {

    /**
     * Create a data filter
     */
    fun data(entityFilter: EntityFilter? = null): EventsFilterBox.Data {
        return EventsFilterBox.Data(
            entityFilter?.let { FilterOptEntityFilter.BySome(it) }
                ?: FilterOptEntityFilter.AcceptAll()
        )
    }

    /**
     * Create a [time based event filter][TimeEventFilter]
     */
    fun time(eventFilter: TimeEventFilter): EventsFilterBox.Time {
        return EventsFilterBox.Time(eventFilter)
    }

    /**
     * Execute a given trigger based on a specified [authority]
     */
    fun executeTrigger(
        triggerId: TriggerId,
        authority: AccountId
    ): EventsFilterBox.ExecuteTrigger {
        return EventsFilterBox.ExecuteTrigger(
            ExecuteTriggerEventFilter(triggerId, authority)
        )
    }

    /**
     * Create an event filter that matches events based on associated [entityKind], [statusKind], [hash],
     * or any combination of these.
     *
     * @see jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.StatusKind
     * @see jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.EntityKind
     */
    fun pipeline(
        entityKind: EntityKind? = null,
        statusKind: StatusKind? = null,
        hash: ByteArray? = null
    ): EventsFilterBox.Pipeline {
        return EventsFilterBox.Pipeline(
            PipelineEventFilter(
                entityKind,
                statusKind,
                hash?.let { Hash(it) }
            )
        )
    }
}

/**
 * `EntityFilters` match events associated with a given entity
 * and either return all matching events or additionally apply another filter to them.
 */
object EntityFilters {

    /**
     * Match events associated with asset definition and apply another filter referenced by
     * its [id][FilterOptOriginFilterAssetDefinitionEvent] or [event type][AssetDefinitionEventFilter]
     */
    fun byAssetDefinition(
        originFilter: OriginFilterAssetDefinitionEvent? = null,
        eventFilter: AssetDefinitionEventFilter? = null
    ): EntityFilter.ByAssetDefinition {
        return EntityFilter.ByAssetDefinition(
            FilterOptAssetDefinitionFilter.BySome(
                AssetDefinitionFilter(
                    originFilter?.let { FilterOptOriginFilterAssetDefinitionEvent.BySome(it) }
                        ?: FilterOptOriginFilterAssetDefinitionEvent.AcceptAll(),
                    eventFilter?.let { FilterOptAssetDefinitionEventFilter.BySome(it) }
                        ?: FilterOptAssetDefinitionEventFilter.AcceptAll()
                )
            )
        )
    }

    /**
     * Match events associated with asset definition
     */
    fun byAssetDefinition(): EntityFilter.ByAssetDefinition {
        return EntityFilter.ByAssetDefinition(
            FilterOptAssetDefinitionFilter.AcceptAll()
        )
    }

    /**
     * Match events associated with accounts and apply another filter referenced by
     * its [id][OriginFilterAccountEvent] or [event type][AccountEventFilter]
     */
    fun byAccount(
        idFilter: OriginFilterAccountEvent? = null,
        eventFilter: AccountEventFilter? = null
    ): EntityFilter.ByAccount {
        return EntityFilter.ByAccount(
            FilterOptAccountFilter.BySome(
                AccountFilter(
                    idFilter?.let { FilterOptOriginFilterAccountEvent.BySome(it) }
                        ?: FilterOptOriginFilterAccountEvent.AcceptAll(),
                    eventFilter?.let { FilterOptAccountEventFilter.BySome(it) }
                        ?: FilterOptAccountEventFilter.AcceptAll()
                )
            )
        )
    }

    /**
     * Match events associated with accounts
     */
    fun byAccount(): EntityFilter.ByAccount {
        return EntityFilter.ByAccount(
            FilterOptAccountFilter.AcceptAll()
        )
    }

    /**
     * Match events associated with assets and apply another filter referenced by
     * its [id][FilterOptOriginFilterAssetEvent] or [event type][AssetEventFilter]
     */
    fun byAsset(
        assetFilter: OriginFilterAssetEvent? = null,
        eventFilter: AssetEventFilter? = null
    ): EntityFilter.ByAsset {
        return EntityFilter.ByAsset(
            FilterOptAssetFilter.BySome(
                AssetFilter(
                    assetFilter?.let { FilterOptOriginFilterAssetEvent.BySome(it) }
                        ?: FilterOptOriginFilterAssetEvent.AcceptAll(),
                    eventFilter?.let { FilterOptAssetEventFilter.BySome(it) }
                        ?: FilterOptAssetEventFilter.AcceptAll()
                )
            )
        )
    }

    /**
     * Match events associated with assets
     */
    fun byAsset(): EntityFilter.ByAsset {
        return EntityFilter.ByAsset(
            FilterOptAssetFilter.AcceptAll()
        )
    }

    /**
     * Match events associated with triggers and apply another filter referenced by
     * its [id][FilterOptOriginFilterTriggerEvent] or [event type][TriggerEventFilter]
     */
    fun byTrigger(
        idFilter: OriginFilterTriggerEvent? = null,
        eventFilter: TriggerEventFilter? = null
    ): EntityFilter.ByTrigger {
        return EntityFilter.ByTrigger(
            FilterOptTriggerFilter.BySome(
                TriggerFilter(
                    idFilter?.let { FilterOptOriginFilterTriggerEvent.BySome(it) }
                        ?: FilterOptOriginFilterTriggerEvent.AcceptAll(),
                    eventFilter?.let { FilterOptTriggerEventFilter.BySome(it) }
                        ?: FilterOptTriggerEventFilter.AcceptAll()
                )
            )
        )
    }

    /**
     * Match events associated with triggers
     */
    fun byTrigger(): EntityFilter.ByTrigger {
        return EntityFilter.ByTrigger(
            FilterOptTriggerFilter.AcceptAll()
        )
    }

    /**
     * Match events associated with domains and apply another filter referenced by
     * its [id][FilterOptOriginFilterDomainEvent] or [event type][DomainEventFilter]
     */
    fun byDomain(
        originFilter: OriginFilterDomainEvent? = null,
        eventFilter: DomainEventFilter? = null
    ): EntityFilter.ByDomain {
        return EntityFilter.ByDomain(
            FilterOptDomainFilter.BySome(
                DomainFilter(
                    originFilter?.let { FilterOptOriginFilterDomainEvent.BySome(it) }
                        ?: FilterOptOriginFilterDomainEvent.AcceptAll(),
                    eventFilter?.let { FilterOptDomainEventFilter.BySome(it) }
                        ?: FilterOptDomainEventFilter.AcceptAll()
                )
            )
        )
    }

    /**
     * Match events associated with domains
     */
    fun byDomain(): EntityFilter.ByDomain {
        return EntityFilter.ByDomain(
            FilterOptDomainFilter.AcceptAll()
        )
    }

    /**
     * Match events associated with peers and apply another filter referenced by
     * its [id][PeerEventOriginFilter] or [event type][PeerEventFilter]
     */
    fun byPeer(
        originFilter: OriginFilterPeerEvent? = null,
        eventFilter: PeerEventFilter? = null
    ): EntityFilter.ByPeer {
        return EntityFilter.ByPeer(
            FilterOptPeerFilter.BySome(
                PeerFilter(
                    originFilter?.let { FilterOptOriginFilterPeerEvent.BySome(it) }
                        ?: FilterOptOriginFilterPeerEvent.AcceptAll(),
                    eventFilter?.let { FilterOptPeerEventFilter.BySome(it) }
                        ?: FilterOptPeerEventFilter.AcceptAll()
                )
            )
        )
    }

    /**
     * Match events associated with peers
     */
    fun byPeer(): EntityFilter.ByPeer {
        return EntityFilter.ByPeer(
            FilterOptPeerFilter.AcceptAll()
        )
    }

    /**
     * Match events associated with roles and apply another filter referenced by
     * its [id][FilterOptOriginFilterRoleEvent] or [event type][RoleEventFilter]
     */
    fun byRole(
        originFilter: OriginFilterRoleEvent? = null,
        eventFilter: RoleEventFilter? = null
    ): EntityFilter.ByRole {
        return EntityFilter.ByRole(
            FilterOptRoleFilter.BySome(
                RoleFilter(
                    originFilter?.let { FilterOptOriginFilterRoleEvent.BySome(it) }
                        ?: FilterOptOriginFilterRoleEvent.AcceptAll(),
                    eventFilter?.let { FilterOptRoleEventFilter.BySome(it) }
                        ?: FilterOptRoleEventFilter.AcceptAll()
                )
            )
        )
    }

    /**
     * Match events associated with roles
     */
    fun byRole(): EntityFilter.ByRole {
        return EntityFilter.ByRole(
            FilterOptRoleFilter.AcceptAll()
        )
    }
}

/**
 * Timed filters
 */
object EventFilters {

    /**
     * Create a filter with a timed execution
     */
    fun timeEventFilter(
        start: Duration,
        period: Duration? = null
    ): TimeEventFilter {
        return TimeEventFilter(
            ExecutionTime.Schedule(
                Schedule(start, period)
            )
        )
    }

    /**
     * Create a pre-commit filter
     */
    fun timeEventFilter(): TimeEventFilter {
        return TimeEventFilter(
            ExecutionTime.PreCommit()
        )
    }
}

/**
 * Query filters
 */
object QueryFilters {

    /**
     * Starts with filter
     */
    fun startsWith(prefix: String): PredicateBox.Raw {
        return PredicateBox.Raw(
            Predicate.Identifiable(QueryPredicate.StartsWith(prefix))
        )
    }

    /**
     * Ends with filter
     */
    fun endsWith(suffix: String): PredicateBox.Raw {
        return PredicateBox.Raw(
            Predicate.Identifiable(QueryPredicate.EndsWith(suffix))
        )
    }

    /**
     * Contains filter
     */
    fun contains(value: String): PredicateBox.Raw {
        return PredicateBox.Raw(
            Predicate.Identifiable(QueryPredicate.Contains(value))
        )
    }

    /**
     * Is filter
     */
    fun `is`(value: String): PredicateBox.Raw {
        return PredicateBox.Raw(
            Predicate.Identifiable(QueryPredicate.Is(value))
        )
    }

    /**
     * Filter for multiple matches (OR)
     */
    fun or(predicates: List<QueryPredicate>): PredicateBox.Or {
        return PredicateBox.Or(
            predicates.map { predicate ->
                PredicateBox.Raw(Predicate.Identifiable(predicate))
            }.toList()
        )
    }

    /**
     * Filter for multiple matches (AND)
     */
    fun and(predicates: List<QueryPredicate>): PredicateBox.And {
        return PredicateBox.And(
            predicates.map { predicate ->
                PredicateBox.Raw(Predicate.Identifiable(predicate))
            }.toList()
        )
    }
}
