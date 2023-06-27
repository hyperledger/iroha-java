package jp.co.soramitsu.iroha2.transaction

import jp.co.soramitsu.iroha2.generated.AccountEventFilter
import jp.co.soramitsu.iroha2.generated.AccountFilter
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.AssetDefinitionEventFilter
import jp.co.soramitsu.iroha2.generated.AssetDefinitionFilter
import jp.co.soramitsu.iroha2.generated.AssetEventFilter
import jp.co.soramitsu.iroha2.generated.AssetFilter
import jp.co.soramitsu.iroha2.generated.DataEntityFilter
import jp.co.soramitsu.iroha2.generated.DomainEventFilter
import jp.co.soramitsu.iroha2.generated.DomainFilter
import jp.co.soramitsu.iroha2.generated.Duration
import jp.co.soramitsu.iroha2.generated.ExecuteTriggerEventFilter
import jp.co.soramitsu.iroha2.generated.ExecutionTime
import jp.co.soramitsu.iroha2.generated.FilterBox
import jp.co.soramitsu.iroha2.generated.FilterOptOfAccountEventFilter
import jp.co.soramitsu.iroha2.generated.FilterOptOfAccountFilter
import jp.co.soramitsu.iroha2.generated.FilterOptOfAssetDefinitionEventFilter
import jp.co.soramitsu.iroha2.generated.FilterOptOfAssetDefinitionFilter
import jp.co.soramitsu.iroha2.generated.FilterOptOfAssetEventFilter
import jp.co.soramitsu.iroha2.generated.FilterOptOfAssetFilter
import jp.co.soramitsu.iroha2.generated.FilterOptOfDataEntityFilter
import jp.co.soramitsu.iroha2.generated.FilterOptOfDomainEventFilter
import jp.co.soramitsu.iroha2.generated.FilterOptOfDomainFilter
import jp.co.soramitsu.iroha2.generated.FilterOptOfOriginFilterOfAccountEvent
import jp.co.soramitsu.iroha2.generated.FilterOptOfOriginFilterOfAssetDefinitionEvent
import jp.co.soramitsu.iroha2.generated.FilterOptOfOriginFilterOfAssetEvent
import jp.co.soramitsu.iroha2.generated.FilterOptOfOriginFilterOfDomainEvent
import jp.co.soramitsu.iroha2.generated.FilterOptOfOriginFilterOfPeerEvent
import jp.co.soramitsu.iroha2.generated.FilterOptOfOriginFilterOfRoleEvent
import jp.co.soramitsu.iroha2.generated.FilterOptOfOriginFilterOfTriggerEvent
import jp.co.soramitsu.iroha2.generated.FilterOptOfPeerEventFilter
import jp.co.soramitsu.iroha2.generated.FilterOptOfPeerFilter
import jp.co.soramitsu.iroha2.generated.FilterOptOfRoleEventFilter
import jp.co.soramitsu.iroha2.generated.FilterOptOfRoleFilter
import jp.co.soramitsu.iroha2.generated.FilterOptOfTriggerEventFilter
import jp.co.soramitsu.iroha2.generated.FilterOptOfTriggerFilter
import jp.co.soramitsu.iroha2.generated.GenericPredicateBox
import jp.co.soramitsu.iroha2.generated.NonTrivial
import jp.co.soramitsu.iroha2.generated.OriginFilterOfAccountEvent
import jp.co.soramitsu.iroha2.generated.OriginFilterOfAssetDefinitionEvent
import jp.co.soramitsu.iroha2.generated.OriginFilterOfAssetEvent
import jp.co.soramitsu.iroha2.generated.OriginFilterOfDomainEvent
import jp.co.soramitsu.iroha2.generated.OriginFilterOfPeerEvent
import jp.co.soramitsu.iroha2.generated.OriginFilterOfRoleEvent
import jp.co.soramitsu.iroha2.generated.OriginFilterOfTriggerEvent
import jp.co.soramitsu.iroha2.generated.PeerEventFilter
import jp.co.soramitsu.iroha2.generated.PeerFilter
import jp.co.soramitsu.iroha2.generated.PipelineEntityKind
import jp.co.soramitsu.iroha2.generated.PipelineEventFilter
import jp.co.soramitsu.iroha2.generated.PipelineStatusKind
import jp.co.soramitsu.iroha2.generated.RoleEventFilter
import jp.co.soramitsu.iroha2.generated.RoleFilter
import jp.co.soramitsu.iroha2.generated.Schedule
import jp.co.soramitsu.iroha2.generated.StringPredicate
import jp.co.soramitsu.iroha2.generated.TimeEventFilter
import jp.co.soramitsu.iroha2.generated.TriggerEventFilter
import jp.co.soramitsu.iroha2.generated.TriggerFilter
import jp.co.soramitsu.iroha2.generated.TriggerId
import jp.co.soramitsu.iroha2.generated.ValuePredicate
import jp.co.soramitsu.iroha2.toIrohaHash

/**
 * Filters are used to filter out events
 */
@Suppress("unused")
object Filters {

    /**
     * Create a data filter
     */
    fun data(entityFilter: DataEntityFilter? = null) = FilterBox.Data(
        entityFilter?.let { FilterOptOfDataEntityFilter.BySome(it) }
            ?: FilterOptOfDataEntityFilter.AcceptAll()
    )

    /**
     * Create a [time based event filter][TimeEventFilter]
     */
    fun time(eventFilter: TimeEventFilter) = FilterBox.Time(eventFilter)

    /**
     * Execute a given trigger based on a specified [authority]
     */
    fun executeTrigger(
        triggerId: TriggerId,
        authority: AccountId
    ) = FilterBox.ExecuteTrigger(
        ExecuteTriggerEventFilter(triggerId, authority)
    )

    /**
     * Create an event filter that matches events based on associated [entityKind], [statusKind], [hash],
     * or any combination of these.
     *
     * @see jp.co.soramitsu.iroha2.generated.pipeline.StatusKind
     * @see jp.co.soramitsu.iroha2.generated.pipeline.EntityKind
     */
    fun pipeline(
        entityKind: PipelineEntityKind? = null,
        statusKind: PipelineStatusKind? = null,
        hash: ByteArray? = null
    ) = FilterBox.Pipeline(
        PipelineEventFilter(
            entityKind,
            statusKind,
            hash?.toIrohaHash()
        )
    )
}

/**
 * `EntityFilters` match events associated with a given entity
 * and either return all matching events or additionally apply another filter to them.
 */
object EntityFilters {

    /**
     * Match events associated with asset definition and apply another filter referenced by
     * its [id][OriginFilterOfAssetDefinitionEvent] or [event type][AssetDefinitionEventFilter]
     */
    fun byAssetDefinition(
        originFilter: OriginFilterOfAssetDefinitionEvent? = null,
        eventFilter: AssetDefinitionEventFilter? = null
    ) = DataEntityFilter.ByAssetDefinition(
        FilterOptOfAssetDefinitionFilter.BySome(
            AssetDefinitionFilter(
                originFilter?.let { FilterOptOfOriginFilterOfAssetDefinitionEvent.BySome(it) }
                    ?: FilterOptOfOriginFilterOfAssetDefinitionEvent.AcceptAll(),
                eventFilter?.let { FilterOptOfAssetDefinitionEventFilter.BySome(it) }
                    ?: FilterOptOfAssetDefinitionEventFilter.AcceptAll()
            )
        )
    )

    /**
     * Match events associated with asset definition
     */
    fun byAssetDefinition() = DataEntityFilter.ByAssetDefinition(FilterOptOfAssetDefinitionFilter.AcceptAll())

    /**
     * Match events associated with accounts and apply another filter referenced by
     * its [id][OriginFilterOfAccountEvent] or [event type][AccountEventFilter]
     */
    fun byAccount(
        idFilter: OriginFilterOfAccountEvent? = null,
        eventFilter: AccountEventFilter? = null
    ) = DataEntityFilter.ByAccount(
        FilterOptOfAccountFilter.BySome(
            AccountFilter(
                idFilter?.let { FilterOptOfOriginFilterOfAccountEvent.BySome(it) }
                    ?: FilterOptOfOriginFilterOfAccountEvent.AcceptAll(),
                eventFilter?.let { FilterOptOfAccountEventFilter.BySome(it) }
                    ?: FilterOptOfAccountEventFilter.AcceptAll()
            )
        )
    )

    /**
     * Match events associated with accounts
     */
    fun byAccount() = DataEntityFilter.ByAccount(FilterOptOfAccountFilter.AcceptAll())

    /**
     * Match events associated with assets and apply another filter referenced by
     * its [id][FilterOptOfOriginFilterOfAssetEvent] or [event type][AssetEventFilter]
     */
    fun byAsset(
        assetFilter: OriginFilterOfAssetEvent? = null,
        eventFilter: AssetEventFilter? = null
    ) = DataEntityFilter.ByAsset(
        FilterOptOfAssetFilter.BySome(
            AssetFilter(
                assetFilter?.let { FilterOptOfOriginFilterOfAssetEvent.BySome(it) }
                    ?: FilterOptOfOriginFilterOfAssetEvent.AcceptAll(),
                eventFilter?.let { FilterOptOfAssetEventFilter.BySome(it) }
                    ?: FilterOptOfAssetEventFilter.AcceptAll()
            )
        )
    )

    /**
     * Match events associated with assets
     */
    fun byAsset() = DataEntityFilter.ByAsset(FilterOptOfAssetFilter.AcceptAll())

    /**
     * Match events associated with triggers and apply another filter referenced by
     * its [id][FilterOptOfOriginFilterOfTriggerEvent] or [event type][TriggerEventFilter]
     */
    fun byTrigger(
        idFilter: OriginFilterOfTriggerEvent? = null,
        eventFilter: TriggerEventFilter? = null
    ) = DataEntityFilter.ByTrigger(
        FilterOptOfTriggerFilter.BySome(
            TriggerFilter(
                idFilter?.let { FilterOptOfOriginFilterOfTriggerEvent.BySome(it) }
                    ?: FilterOptOfOriginFilterOfTriggerEvent.AcceptAll(),
                eventFilter?.let { FilterOptOfTriggerEventFilter.BySome(it) }
                    ?: FilterOptOfTriggerEventFilter.AcceptAll()
            )
        )
    )

    /**
     * Match events associated with triggers
     */
    fun byTrigger() = DataEntityFilter.ByTrigger(FilterOptOfTriggerFilter.AcceptAll())

    /**
     * Match events associated with domains and apply another filter referenced by
     * its [id][FilterOptOfOriginFilterOfDomainEvent] or [event type][DomainEventFilter]
     */
    fun byDomain(
        originFilter: OriginFilterOfDomainEvent? = null,
        eventFilter: DomainEventFilter? = null
    ) = DataEntityFilter.ByDomain(
        FilterOptOfDomainFilter.BySome(
            DomainFilter(
                originFilter?.let { FilterOptOfOriginFilterOfDomainEvent.BySome(it) }
                    ?: FilterOptOfOriginFilterOfDomainEvent.AcceptAll(),
                eventFilter?.let { FilterOptOfDomainEventFilter.BySome(it) }
                    ?: FilterOptOfDomainEventFilter.AcceptAll()
            )
        )
    )

    /**
     * Match events associated with domains
     */
    fun byDomain() = DataEntityFilter.ByDomain(FilterOptOfDomainFilter.AcceptAll())

    /**
     * Match events associated with peers and apply another filter referenced by
     * its [id][FilterOptOfOriginFilterOfPeerEvent] or [event type][PeerEventFilter]
     */
    fun byPeer(
        originFilter: OriginFilterOfPeerEvent? = null,
        eventFilter: PeerEventFilter? = null
    ) = DataEntityFilter.ByPeer(
        FilterOptOfPeerFilter.BySome(
            PeerFilter(
                originFilter?.let { FilterOptOfOriginFilterOfPeerEvent.BySome(it) }
                    ?: FilterOptOfOriginFilterOfPeerEvent.AcceptAll(),
                eventFilter?.let { FilterOptOfPeerEventFilter.BySome(it) }
                    ?: FilterOptOfPeerEventFilter.AcceptAll()
            )
        )
    )

    /**
     * Match events associated with peers
     */
    fun byPeer() = DataEntityFilter.ByPeer(FilterOptOfPeerFilter.AcceptAll())

    /**
     * Match events associated with roles and apply another filter referenced by
     * its [id][FilterOptOriginFilterRoleEvent] or [event type][RoleEventFilter]
     */
    fun byRole(
        originFilter: OriginFilterOfRoleEvent? = null,
        eventFilter: RoleEventFilter? = null
    ) = DataEntityFilter.ByRole(
        FilterOptOfRoleFilter.BySome(
            RoleFilter(
                originFilter?.let { FilterOptOfOriginFilterOfRoleEvent.BySome(it) }
                    ?: FilterOptOfOriginFilterOfRoleEvent.AcceptAll(),
                eventFilter?.let { FilterOptOfRoleEventFilter.BySome(it) }
                    ?: FilterOptOfRoleEventFilter.AcceptAll()
            )
        )
    )

    /**
     * Match events associated with roles
     */
    fun byRole() = DataEntityFilter.ByRole(FilterOptOfRoleFilter.AcceptAll())
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
    ) = TimeEventFilter(ExecutionTime.Schedule(Schedule(start, period)))

    /**
     * Create a pre-commit filter
     */
    fun timeEventFilter() = TimeEventFilter(ExecutionTime.PreCommit())
}

/**
 * Query filters
 */
object QueryFilters {

    /**
     * Starts with filter
     */
    fun startsWith(prefix: String) = GenericPredicateBox.Raw(
        ValuePredicate.Identifiable(StringPredicate.StartsWith(prefix))
    )

    /**
     * Ends with filter
     */
    fun endsWith(suffix: String) = GenericPredicateBox.Raw(
        ValuePredicate.Identifiable(StringPredicate.EndsWith(suffix))
    )

    /**
     * Contains filter
     */
    fun contains(value: String) = GenericPredicateBox.Raw(
        ValuePredicate.Identifiable(StringPredicate.Contains(value))
    )

    /**
     * Is filter
     */
    fun `is`(value: String) = GenericPredicateBox.Raw(
        ValuePredicate.Identifiable(StringPredicate.Is(value))
    )

    /**
     * Filter for multiple matches (OR)
     */
    fun or(vararg predicates: StringPredicate) = predicates
        .map { GenericPredicateBox.Raw(ValuePredicate.Identifiable(it)) }.toList()
        .let { NonTrivial<GenericPredicateBox<ValuePredicate>>(it) }
        .let { GenericPredicateBox.Or(it) }

    /**
     * Filter for multiple matches (AND)
     */
    fun and(vararg predicates: StringPredicate) = predicates
        .map { GenericPredicateBox.Raw(ValuePredicate.Identifiable(it)) }.toList()
        .let { NonTrivial<GenericPredicateBox<ValuePredicate>>(it) }
        .let { GenericPredicateBox.And(it) }
}
