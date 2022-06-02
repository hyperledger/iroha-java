package jp.co.soramitsu.iroha2.transaction

import jp.co.soramitsu.iroha2.generated.Duration
import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.generated.datamodel.events.FilterBox
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
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.EntityKind
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.StatusKind
import jp.co.soramitsu.iroha2.generated.datamodel.events.time.ExecutionTime
import jp.co.soramitsu.iroha2.generated.datamodel.events.time.Schedule
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.events.executetrigger.EventFilter as ExecuteTriggerEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.EventFilter as PipelineEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.events.time.EventFilter as TimeEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id as TriggerId

object Filters {
    fun data(entityFilter: EntityFilter? = null): FilterBox.Data {
        return FilterBox.Data(
            entityFilter?.let { FilterOptEntityFilter.BySome(it) }
                ?: FilterOptEntityFilter.AcceptAll()
        )
    }

    fun time(eventFilter: TimeEventFilter): FilterBox.Time {
        return FilterBox.Time(eventFilter)
    }

    fun executeTrigger(
        triggerId: TriggerId,
        authority: AccountId
    ): FilterBox.ExecuteTrigger {
        return FilterBox.ExecuteTrigger(
            ExecuteTriggerEventFilter(triggerId, authority)
        )
    }

    fun pipeline(
        entityKind: EntityKind? = null,
        statusKind: StatusKind? = null,
        hash: ByteArray? = null
    ): FilterBox.Pipeline {
        return FilterBox.Pipeline(
            PipelineEventFilter(
                entityKind, statusKind, hash?.let { Hash(it) }
            )
        )
    }
}

object EntityFilters {

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

    fun byAssetDefinition(): EntityFilter.ByAssetDefinition {
        return EntityFilter.ByAssetDefinition(
            FilterOptAssetDefinitionFilter.AcceptAll()
        )
    }

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

    fun byAccount(): EntityFilter.ByAccount {
        return EntityFilter.ByAccount(
            FilterOptAccountFilter.AcceptAll()
        )
    }

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

    fun byAsset(): EntityFilter.ByAsset {
        return EntityFilter.ByAsset(
            FilterOptAssetFilter.AcceptAll()
        )
    }

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

    fun byTrigger(): EntityFilter.ByTrigger {
        return EntityFilter.ByTrigger(
            FilterOptTriggerFilter.AcceptAll()
        )
    }

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

    fun byDomain(): EntityFilter.ByDomain {
        return EntityFilter.ByDomain(
            FilterOptDomainFilter.AcceptAll()
        )
    }

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

    fun byPeer(): EntityFilter.ByPeer {
        return EntityFilter.ByPeer(
            FilterOptPeerFilter.AcceptAll()
        )
    }

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

    fun byRole(): EntityFilter.ByRole {
        return EntityFilter.ByRole(
            FilterOptRoleFilter.AcceptAll()
        )
    }
}

object EventFilters {
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

    fun timeEventFilter(): TimeEventFilter {
        return TimeEventFilter(
            ExecutionTime.PreCommit()
        )
    }
}
