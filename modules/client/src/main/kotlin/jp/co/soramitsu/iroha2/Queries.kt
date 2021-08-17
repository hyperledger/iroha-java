package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountById
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetById
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

object Queries {

    fun findAccountById(accountId: AccountId): QueryBox {
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

    fun findAccountById(name: String, domain: String) = findAccountById(AccountId(name, domain))

    fun findAssetById(assetId: AssetId): QueryBox {
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

    fun findAssetById(assetName: String, assetDomain: String, accountName: String, accountDomain: String): QueryBox {
        return findAssetById(
            AssetId(
                definitionId = DefinitionId(assetName, assetDomain),
                accountId = AccountId(accountName, accountDomain)
            )
        )
    }
}
