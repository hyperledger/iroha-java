package jp.co.soramitsu.iroha2.testcontainers

import com.google.gson.GsonBuilder
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.genesis.GenesisTransaction
import jp.co.soramitsu.iroha2.generated.genesis.RawGenesisBlock
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption.CREATE
import java.nio.file.StandardOpenOption.TRUNCATE_EXISTING
import kotlin.text.Charsets.UTF_8

class GenesisBuilder private constructor() {

    private val genesisTxs = lazy { ArrayList<GenesisTransaction>() }

    fun withInstructions(vararg instructions: Instruction) : GenesisBuilder {
        genesisTxs.value.add(GenesisTransaction(instructions.toMutableList()))
        return this
    }

    fun withTransactions(vararg transactions: GenesisTransaction) : GenesisBuilder {
        genesisTxs.value.addAll(transactions.toMutableList())
        return this
    }

    fun build() = Genesis(RawGenesisBlock(genesisTxs.value))

    companion object {
        fun builder() = GenesisBuilder()
    }
}

class Genesis(private val genesisBlock: RawGenesisBlock) {

    fun writeToFile(path: Path) = Files.write(path, asJson().toByteArray(UTF_8), TRUNCATE_EXISTING, CREATE)

    fun asJson() = GsonBuilder()
        .setPrettyPrinting()
        .create()
        .toJson(genesisBlock)

}
