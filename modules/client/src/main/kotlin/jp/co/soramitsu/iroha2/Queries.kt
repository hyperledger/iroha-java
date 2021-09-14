package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountById
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetById
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByAssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByDomainName
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

object Queries {

    fun findAccountById(accountId: AccountId): QueryBox.FindAccountById {
        return QueryBox.FindAccountById(
            FindAccountById(
                EvaluatesTo(
                    Expression.Raw(
                        Value.Id(
                            IdBox.AccountId(
                                accountId
                            )
                        )
                    )
                )
            )
        )
    }

    fun findAssetById(assetId: AssetId): QueryBox.FindAssetById {
        return QueryBox.FindAssetById(
            FindAssetById(
                EvaluatesTo(
                    Expression.Raw(
                        Value.Id(
                            IdBox.AssetId(
                                assetId
                            )
                        )
                    )
                )
            )
        )
    }

    fun findAssetsByDomainName(domain: String): QueryBox.FindAssetsByDomainName {
        return QueryBox.FindAssetsByDomainName(
            FindAssetsByDomainName(
                EvaluatesTo(
                    Expression.Raw(
                        Value.String(domain)
                    )
                )
            )
        )
    }

    fun findAssetsByAssetDefinitionId(assetDefinition: DefinitionId): QueryBox.FindAssetsByAssetDefinitionId {
        return QueryBox.FindAssetsByAssetDefinitionId(
            FindAssetsByAssetDefinitionId(
                EvaluatesTo(
                    Expression.Raw(
                        Value.Id(IdBox.AssetDefinitionId(assetDefinition))
                    )
                )
            )
        )
    }
}
