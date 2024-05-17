package jp.co.soramitsu.iroha2.testengine

import jp.co.soramitsu.iroha2.Genesis
import jp.co.soramitsu.iroha2.generated.RawGenesisBlockFile
import org.junit.jupiter.api.Test
import java.lang.annotation.Inherited
import kotlin.reflect.KClass

/**
 * A test marked with this annotation awaits Iroha's deployment
 *
 * @param sources Genesis will be composed of the sources unique instructions
 * @param amount Number of peers
 */
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Test
@Inherited
annotation class WithIroha(
    val sources: Array<KClass<out Genesis>> = [EmptyGenesis::class],
    val configs: Array<String> = [],
    val source: String = "",
    val amount: Int = 1,
    val fetchSize: Int = 10,
    val executorSource: String = "",
)

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Test
@Inherited
annotation class WithIrohaManual(
    val apiUrls: Array<String> = [],
    val telemetryUrls: Array<String> = [],
    val peerUrls: Array<String> = [],
    val account: String = "",
    val publicKey: String = "",
    val privateKey: String = "",
    val dockerComposeFile: String = "",
)

/**
 * Empty genesis with no instructions
 */
open class EmptyGenesis : Genesis(
    RawGenesisBlockFile(emptyList(), executorFile = EXECUTOR_FILE_NAME),
)

const val IROHA_CONFIG_DELIMITER = "="
