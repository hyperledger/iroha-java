package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.core.genesis.GenesisTransaction
import jp.co.soramitsu.iroha2.generated.core.genesis.RawGenesisBlock
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

/**
 * Genesis block is used to initialise a blockchain
 */
open class Genesis(open val genesisBlock: RawGenesisBlock) {

    /**
     * Write genesis to file
     */
    fun writeToFile(path: Path): Path = Files.write(
        path,
        asJson().toByteArray(Charsets.UTF_8),
        StandardOpenOption.TRUNCATE_EXISTING,
        StandardOpenOption.CREATE
    )

    /**
     * Represent genesis as JSON
     */
    fun asJson(): String = JSON_SERDE.writeValueAsString(this.genesisBlock)

    companion object {
        /**
         * Return empty genesis
         */
        fun getEmpty() = Genesis(RawGenesisBlock(listOf(GenesisTransaction(listOf()))))
    }
}
