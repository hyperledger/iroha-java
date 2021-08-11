package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountById

object Queries {

    fun findAccountById(name: String, domain: String): QueryBox {
        return QueryBox.FindAccountById(
            FindAccountById(
                EvaluatesTo(
                    Expression.Raw(
                        Value.Id(
                            IdBox.AccountId(
                                Id(name, domain)
                            )
                        )
                    )
                )
            )
        )
    }
}
