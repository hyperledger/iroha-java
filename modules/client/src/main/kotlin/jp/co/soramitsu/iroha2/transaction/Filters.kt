package jp.co.soramitsu.iroha2.transaction

import jp.co.soramitsu.iroha2.generated.Duration
import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.events.EventsFilterBox
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
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptIdFilterAccountId
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptIdFilterAssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptIdFilterAssetId
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptIdFilterDomainId
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptIdFilterPeerId
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptIdFilterRoleId
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptIdFilterTriggerId
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptPeerEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptPeerFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptRoleEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptRoleFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptTriggerEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.FilterOptTriggerFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.IdFilterAccountId
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.IdFilterAssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.IdFilterAssetId
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.IdFilterDomainId
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.IdFilterPeerId
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.IdFilterRoleId
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.IdFilterTriggerId
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.account.AccountEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.account.AccountFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.asset.AssetDefinitionEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.asset.AssetDefinitionFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.asset.AssetEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.asset.AssetFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.domain.DomainEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.domain.DomainFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.peer.PeerEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.peer.PeerFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.role.RoleEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.role.RoleFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.trigger.TriggerEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.filters.trigger.TriggerFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.executetrigger.ExecuteTriggerEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.EntityKind
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.PipelineEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.StatusKind
import jp.co.soramitsu.iroha2.generated.datamodel.events.time.ExecutionTime
import jp.co.soramitsu.iroha2.generated.datamodel.events.time.Schedule
import jp.co.soramitsu.iroha2.generated.datamodel.events.time.TimeEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId

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
                entityKind, statusKind, hash?.let { Hash(it) }
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
     * its [id][IdFilterAssetDefinitionId] or [event type][AssetDefinitionEventFilter]
     */
    fun byAssetDefinition(
        idFilter: IdFilterAssetDefinitionId? = null,
        eventFilter: AssetDefinitionEventFilter? = null
    ): EntityFilter.ByAssetDefinition {
        return EntityFilter.ByAssetDefinition(
            FilterOptAssetDefinitionFilter.BySome(
                AssetDefinitionFilter(
                    idFilter?.let { FilterOptIdFilterAssetDefinitionId.BySome(it) }
                        ?: FilterOptIdFilterAssetDefinitionId.AcceptAll(),
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
     * its [id][IdFilterAccountId] or [event type][AccountEventFilter]
     */
    fun byAccount(
        idFilter: IdFilterAccountId? = null,
        eventFilter: AccountEventFilter? = null
    ): EntityFilter.ByAccount {
        return EntityFilter.ByAccount(
            FilterOptAccountFilter.BySome(
                AccountFilter(
                    idFilter?.let { FilterOptIdFilterAccountId.BySome(it) }
                        ?: FilterOptIdFilterAccountId.AcceptAll(),
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
     * its [id][IdFilterAssetId] or [event type][AssetEventFilter]
     */
    fun byAsset(
        idFilter: IdFilterAssetId? = null,
        eventFilter: AssetEventFilter? = null
    ): EntityFilter.ByAsset {
        return EntityFilter.ByAsset(
            FilterOptAssetFilter.BySome(
                AssetFilter(
                    idFilter?.let { FilterOptIdFilterAssetId.BySome(it) }
                        ?: FilterOptIdFilterAssetId.AcceptAll(),
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
     * its [id][IdFilterTriggerId] or [event type][TriggerEventFilter]
     */
    fun byTrigger(
        idFilter: IdFilterTriggerId? = null,
        eventFilter: TriggerEventFilter? = null
    ): EntityFilter.ByTrigger {
        return EntityFilter.ByTrigger(
            FilterOptTriggerFilter.BySome(
                TriggerFilter(
                    idFilter?.let { FilterOptIdFilterTriggerId.BySome(it) }
                        ?: FilterOptIdFilterTriggerId.AcceptAll(),
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
     * its [id][IdFilterDomainId] or [event type][DomainEventFilter]
     */
    fun byDomain(
        idFilter: IdFilterDomainId? = null,
        eventFilter: DomainEventFilter? = null
    ): EntityFilter.ByDomain {
        return EntityFilter.ByDomain(
            FilterOptDomainFilter.BySome(
                DomainFilter(
                    idFilter?.let { FilterOptIdFilterDomainId.BySome(it) }
                        ?: FilterOptIdFilterDomainId.AcceptAll(),
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
     * its [id][IdFilterPeerId] or [event type][PeerEventFilter]
     */
    fun byPeer(
        idFilter: IdFilterPeerId? = null,
        eventFilter: PeerEventFilter? = null
    ): EntityFilter.ByPeer {
        return EntityFilter.ByPeer(
            FilterOptPeerFilter.BySome(
                PeerFilter(
                    idFilter?.let { FilterOptIdFilterPeerId.BySome(it) }
                        ?: FilterOptIdFilterPeerId.AcceptAll(),
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
     * its [id][IdFilterRoleId] or [event type][RoleEventFilter]
     */
    fun byRole(
        idFilter: IdFilterRoleId? = null,
        eventFilter: RoleEventFilter? = null
    ): EntityFilter.ByRole {
        return EntityFilter.ByRole(
            FilterOptRoleFilter.BySome(
                RoleFilter(
                    idFilter?.let { FilterOptIdFilterRoleId.BySome(it) }
                        ?: FilterOptIdFilterRoleId.AcceptAll(),
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
