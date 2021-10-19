package jp.co.soramitsu.iroha2.testcontainers.genesis

import jp.co.soramitsu.iroha2.generated.core.genesis.GenesisTransaction
import jp.co.soramitsu.iroha2.generated.core.genesis.RawGenesisBlock
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction

class GenesisBuilder private constructor() {

    private val genesisTxs = lazy { ArrayList<GenesisTransaction>() }

    fun withInstructions(vararg instructions: Instruction): GenesisBuilder {
        genesisTxs.value.add(GenesisTransaction(instructions.toList()))
        return this
    }

    fun withTransactions(vararg transactions: GenesisTransaction): GenesisBuilder {
        genesisTxs.value.addAll(transactions.toList())
        return this
    }

    fun build() = Genesis(RawGenesisBlock(genesisTxs.value))

    companion object {
        fun builder() = GenesisBuilder()
    }
}
