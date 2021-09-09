package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountById
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetById
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByDomainName
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

    fun findAssetsByDomainName(domain: String): QueryBox {
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
}
