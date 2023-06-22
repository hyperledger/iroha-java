package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.Pagination
import java.math.BigInteger

/**
 * Pages contain query results with extracted [data].
 */
data class Page<T>(val data: T, val pagination: Pagination, val total: BigInteger)
