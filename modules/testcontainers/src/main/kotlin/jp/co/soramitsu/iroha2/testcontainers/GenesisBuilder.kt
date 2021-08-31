package jp.co.soramitsu.iroha2.testcontainers

import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.genesis.GenesisTransaction
import jp.co.soramitsu.iroha2.generated.genesis.RawGenesisBlock

class GenesisBuilder private constructor() {

    private val genesisTxs = lazy { ArrayList<GenesisTransaction>() }

    fun withInstructions(vararg instructions: Instruction): GenesisBuilder {
        genesisTxs.value.add(GenesisTransaction(instructions.toMutableList()))
        return this
    }

    fun withTransactions(vararg transactions: GenesisTransaction): GenesisBuilder {
        genesisTxs.value.addAll(transactions.toMutableList())
        return this
    }

    fun build() = Genesis(RawGenesisBlock(genesisTxs.value))

    companion object {
        fun builder() = GenesisBuilder()
    }
}
