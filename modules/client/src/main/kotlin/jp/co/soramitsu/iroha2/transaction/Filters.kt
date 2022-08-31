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
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptEntityFilter
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
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptEventsDataEventsAccountAccountEventFilter as FilterOptAccountEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptEventsDataEventsAccountAccountFilter as FilterOptAccountFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptEventsDataEventsAssetAssetDefinitionEventFilter as FilterOptAssetDefinitionEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptEventsDataEventsAssetAssetDefinitionFilter as FilterOptAssetDefinitionFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptEventsDataEventsAssetAssetEventFilter as FilterOptAssetEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptEventsDataEventsAssetAssetFilter as FilterOptAssetFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptEventsDataEventsDomainDomainEventFilter as FilterOptDomainEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptEventsDataEventsDomainDomainFilter as FilterOptDomainFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptEventsDataEventsPeerPeerEventFilter as FilterOptPeerEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptEventsDataEventsPeerPeerFilter as FilterOptPeerFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptEventsDataEventsRoleRoleEventFilter as FilterOptRoleEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptEventsDataEventsRoleRoleFilter as FilterOptRoleFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptEventsDataEventsTriggerTriggerEventFilter as FilterOptTriggerEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptEventsDataEventsTriggerTriggerFilter as FilterOptTriggerFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptOriginFilterEventsDataEventsAccountAccountEvent as FilterOptAccountEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptOriginFilterEventsDataEventsAssetAssetDefinitionEvent as FilterOptAssetDefinitionEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptOriginFilterEventsDataEventsAssetAssetEvent as FilterOptAssetEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptOriginFilterEventsDataEventsDomainDomainEvent as FilterOptDomainEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptOriginFilterEventsDataEventsPeerPeerEvent as FilterOptPeerEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptOriginFilterEventsDataEventsRoleRoleEvent as FilterOptRoleEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptOriginFilterEventsDataEventsTriggerTriggerEvent as FilterOptTriggerEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.OriginFilterEventsDataEventsAccountAccountEvent as AccountEventOriginFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.OriginFilterEventsDataEventsAssetAssetDefinitionEvent as AssetDefinitionEventOriginFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.OriginFilterEventsDataEventsAssetAssetEvent as AssetEventOriginFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.OriginFilterEventsDataEventsDomainDomainEvent as DomainEventOriginFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.OriginFilterEventsDataEventsPeerPeerEvent as PeerEventOriginFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.OriginFilterEventsDataEventsRoleRoleEvent as RoleEventOriginFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.OriginFilterEventsDataEventsTriggerTriggerEvent as TriggerEventOriginFilter
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.string.Predicate as QueryPredicate

/**
 * Filters are used to filter out events
 */
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
     * its [id][AssetDefinitionEventOriginFilter] or [event type][AssetDefinitionEventFilter]
     */
    fun byAssetDefinition(
        originFilter: AssetDefinitionEventOriginFilter? = null,
        eventFilter: AssetDefinitionEventFilter? = null
    ): EntityFilter.ByAssetDefinition {
        return EntityFilter.ByAssetDefinition(
            FilterOptAssetDefinitionFilter.BySome(
                AssetDefinitionFilter(
                    originFilter?.let { FilterOptAssetDefinitionEvent.BySome(it) }
                        ?: FilterOptAssetDefinitionEvent.AcceptAll(),
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
     * its [id][AccountEventOriginFilter] or [event type][AccountEventFilter]
     */
    fun byAccount(
        idFilter: AccountEventOriginFilter? = null,
        eventFilter: AccountEventFilter? = null
    ): EntityFilter.ByAccount {
        return EntityFilter.ByAccount(
            FilterOptAccountFilter.BySome(
                AccountFilter(
                    idFilter?.let { FilterOptAccountEvent.BySome(it) }
                        ?: FilterOptAccountEvent.AcceptAll(),
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
     * its [id][AssetEventOriginFilter] or [event type][AssetEventFilter]
     */
    fun byAsset(
        assetFilter: AssetEventOriginFilter? = null,
        eventFilter: AssetEventFilter? = null
    ): EntityFilter.ByAsset {
        return EntityFilter.ByAsset(
            FilterOptAssetFilter.BySome(
                AssetFilter(
                    assetFilter?.let { FilterOptAssetEvent.BySome(it) }
                        ?: FilterOptAssetEvent.AcceptAll(),
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
     * its [id][TriggerEventOriginFilter] or [event type][TriggerEventFilter]
     */
    fun byTrigger(
        idFilter: TriggerEventOriginFilter? = null,
        eventFilter: TriggerEventFilter? = null
    ): EntityFilter.ByTrigger {
        return EntityFilter.ByTrigger(
            FilterOptTriggerFilter.BySome(
                TriggerFilter(
                    idFilter?.let { FilterOptTriggerEvent.BySome(it) }
                        ?: FilterOptTriggerEvent.AcceptAll(),
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
     * its [id][DomainEventOriginFilter] or [event type][DomainEventFilter]
     */
    fun byDomain(
        originFilter: DomainEventOriginFilter? = null,
        eventFilter: DomainEventFilter? = null
    ): EntityFilter.ByDomain {
        return EntityFilter.ByDomain(
            FilterOptDomainFilter.BySome(
                DomainFilter(
                    originFilter?.let { FilterOptDomainEvent.BySome(it) }
                        ?: FilterOptDomainEvent.AcceptAll(),
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
        originFilter: PeerEventOriginFilter? = null,
        eventFilter: PeerEventFilter? = null
    ): EntityFilter.ByPeer {
        return EntityFilter.ByPeer(
            FilterOptPeerFilter.BySome(
                PeerFilter(
                    originFilter?.let { FilterOptPeerEvent.BySome(it) }
                        ?: FilterOptPeerEvent.AcceptAll(),
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
     * its [id][RoleEventOriginFilter] or [event type][RoleEventFilter]
     */
    fun byRole(
        originFilter: RoleEventOriginFilter? = null,
        eventFilter: RoleEventFilter? = null
    ): EntityFilter.ByRole {
        return EntityFilter.ByRole(
            FilterOptRoleFilter.BySome(
                RoleFilter(
                    originFilter?.let { FilterOptRoleEvent.BySome(it) }
                        ?: FilterOptRoleEvent.AcceptAll(),
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
     * Create a starts with filter
     */
    fun startsWith(
        id: String
    ): PredicateBox.Raw {
        return PredicateBox.Raw(
            Predicate.Identifiable(
                QueryPredicate.StartsWith(
                    id
                )
            )
        )
    }
}
