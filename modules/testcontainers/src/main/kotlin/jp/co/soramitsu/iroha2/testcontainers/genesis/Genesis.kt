package jp.co.soramitsu.iroha2.testcontainers.genesis

import jp.co.soramitsu.iroha2.generated.core.genesis.RawGenesisBlock
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

open class Genesis(open val genesisBlock: RawGenesisBlock) {

    fun writeToFile(path: Path) = Files.write(
        path,
        asJson().toByteArray(Charsets.UTF_8),
        StandardOpenOption.TRUNCATE_EXISTING,
        StandardOpenOption.CREATE
    )

    fun asJson() = GenesisJsonSerializer.asJson(this)
}
