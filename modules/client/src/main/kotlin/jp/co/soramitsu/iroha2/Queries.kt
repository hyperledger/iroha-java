package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountById
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountKeyValueByIdAndKey
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountsByDomainName
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountsByName
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAllAccounts
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAllAssets
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAllAssetsDefinitions
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetById
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetKeyValueByIdAndKey
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetQuantityById
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByAccountId
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByAssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByDomainName
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByDomainNameAndAssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByName
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

object Queries {

    fun findAllAccounts(): QueryBox.FindAllAccounts {
        return QueryBox.FindAllAccounts(
            FindAllAccounts()
        )
    }

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

    fun findAccountKeyValueByIdAndKey(accountId: AccountId, key: String): QueryBox.FindAccountKeyValueByIdAndKey {
        return QueryBox.FindAccountKeyValueByIdAndKey(
            FindAccountKeyValueByIdAndKey(
                EvaluatesTo(
                    Expression.Raw(
                        Value.Id(
                            IdBox.AccountId(accountId)
                        )
                    )
                ),
                EvaluatesTo(
                    Expression.Raw(
                        Value.String(key)
                    )
                )
            )
        )
    }

    fun findAccountsByName(name: String): QueryBox.FindAccountsByName {
        return QueryBox.FindAccountsByName(
            FindAccountsByName(
                EvaluatesTo(
                    Expression.Raw(
                        Value.String(name)
                    )
                )
            )
        )
    }

    fun findAccountsByDomainName(domain: String): QueryBox.FindAccountsByDomainName {
        return QueryBox.FindAccountsByDomainName(
            FindAccountsByDomainName(
                EvaluatesTo(
                    Expression.Raw(
                        Value.String(domain)
                    )
                )
            )
        )
    }

    fun findAllAssets(): QueryBox.FindAllAssets {
        return QueryBox.FindAllAssets(
            FindAllAssets()
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

    fun findAssetsByName(name: String): QueryBox.FindAssetsByName {
        return QueryBox.FindAssetsByName(
            FindAssetsByName(
                EvaluatesTo(
                    Expression.Raw(
                        Value.String(name)
                    )
                )
            )
        )
    }

    fun findAssetsByAccountId(accountId: AccountId): QueryBox.FindAssetsByAccountId {
        return QueryBox.FindAssetsByAccountId(
            FindAssetsByAccountId(
                EvaluatesTo(
                    Expression.Raw(
                        Value.Id(
                            IdBox.AccountId(accountId)
                        )
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
                        Value.Id(
                            IdBox.AssetDefinitionId(assetDefinition)
                        )
                    )
                )
            )
        )
    }

    fun findAssetsByDomainNameAndAssetDefinitionId(
        domain: String,
        assetDefinition: DefinitionId
    ): QueryBox.FindAssetsByDomainNameAndAssetDefinitionId {
        return QueryBox.FindAssetsByDomainNameAndAssetDefinitionId(
            FindAssetsByDomainNameAndAssetDefinitionId(
                EvaluatesTo(
                    Expression.Raw(
                        Value.String(domain)
                    )
                ),
                EvaluatesTo(
                    Expression.Raw(
                        Value.Id(
                            IdBox.AssetDefinitionId(assetDefinition)
                        )
                    )
                )
            )
        )
    }

    fun findAllAssetsDefinitions(): QueryBox.FindAllAssetsDefinitions {
        return QueryBox.FindAllAssetsDefinitions(
            FindAllAssetsDefinitions()
        )
    }

    fun findAssetQuantityById(assetId: AssetId): QueryBox.FindAssetQuantityById {
        return QueryBox.FindAssetQuantityById(
            FindAssetQuantityById(
                EvaluatesTo(
                    Expression.Raw(
                        Value.Id(
                            IdBox.AssetId(assetId)
                        )
                    )
                )
            )
        )
    }

    fun findAssetKeyValueByIdAndKey(assetId: AssetId, key: String): QueryBox.FindAssetKeyValueByIdAndKey {
        return QueryBox.FindAssetKeyValueByIdAndKey(
            FindAssetKeyValueByIdAndKey(
                EvaluatesTo(
                    Expression.Raw(
                        Value.Id(
                            IdBox.AssetId(assetId)
                        )
                    )
                ),
                EvaluatesTo(
                    Expression.Raw(
                        Value.String(key)
                    )
                )
            )
        )
    }
}
