package jp.co.soramitsu.iroha2.engine

import jp.co.soramitsu.iroha2.Genesis
import jp.co.soramitsu.iroha2.generated.core.genesis.GenesisTransaction
import jp.co.soramitsu.iroha2.generated.core.genesis.RawGenesisBlock
import org.junit.jupiter.api.Test
import java.lang.annotation.Inherited
import kotlin.reflect.KClass

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Test
@Inherited
annotation class WithIroha(val genesis: KClass<out Genesis> = EmptyGenesis::class)

/**
 * Empty genesis with no instructions
 */
open class EmptyGenesis : Genesis(RawGenesisBlock(listOf(GenesisTransaction(emptyList()))))
