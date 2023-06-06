package jp.co.soramitsu.iroha2.testengine

import jp.co.soramitsu.iroha2.Genesis
import jp.co.soramitsu.iroha2.generated.RawGenesisBlock
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
    val amount: Int = 1
)

/**
 * Empty genesis with no instructions
 */
open class EmptyGenesis : Genesis(
    RawGenesisBlock(listOf(emptyList()), validatorMode)
)

const val IROHA_CONFIG_DELIMITER = "="
