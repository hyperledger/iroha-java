package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.crypto.Signature
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Transaction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction._VersionedTransactionV1

fun main() {
    VersionedTransaction.V1(_VersionedTransactionV1(Transaction(
        Payload(Id("foo", "bar"), mutableListOf(), 1024U, 1024U, mutableMapOf()),
        mutableListOf(Signature(PublicKey("ed22", mutableListOf()), mutableListOf()))))
    )
}
