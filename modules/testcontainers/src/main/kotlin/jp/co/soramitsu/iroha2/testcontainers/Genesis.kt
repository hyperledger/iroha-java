package jp.co.soramitsu.iroha2.testcontainers

import com.google.gson.GsonBuilder
import jp.co.soramitsu.iroha2.generated.genesis.RawGenesisBlock
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

class Genesis(val genesisBlock: RawGenesisBlock) {

    fun writeToFile(path: Path) = Files.write(
        path,
        asJson().toByteArray(Charsets.UTF_8),
        StandardOpenOption.TRUNCATE_EXISTING,
        StandardOpenOption.CREATE
    )

    fun asJson() = GenesisJsonSerializer.asJson(this)

}
