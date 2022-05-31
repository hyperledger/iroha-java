package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.datamodel.pagination.Pagination
import java.math.BigInteger

data class Page<T>(val data: T, val pagination: Pagination, val total: BigInteger)
