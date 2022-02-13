package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.core.genesis.RawGenesisBlock
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

/**
 * Init block
 */
open class Genesis(open val genesisBlock: RawGenesisBlock) {

    /**
     * Writes to genesis file
     */
    fun writeToFile(path: Path) = Files.write(
        path,
        asJson().toByteArray(Charsets.UTF_8),
        StandardOpenOption.TRUNCATE_EXISTING,
        StandardOpenOption.CREATE
    )

    /**
     * Represents genesis as JSON
     */
    fun asJson() = JSON_SERDE.writeValueAsString(this.genesisBlock)
}
