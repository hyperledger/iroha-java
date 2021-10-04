package jp.co.soramitsu.iroha2.engine

import jp.co.soramitsu.iroha2.testcontainers.genesis.Genesis
import org.junit.jupiter.api.Test
import java.lang.annotation.Inherited
import kotlin.reflect.KClass

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Test
@Inherited
annotation class WithIroha(val genesis: KClass<out Genesis> = DefaultGenesis::class)
